/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.code.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.favourite.collections.infrastructure.code.data.CodeValueData;
import com.favourite.collections.infrastructure.code.domain.CodeValue;
import com.favourite.collections.infrastructure.code.repository.CodeValueRepository;
import com.favourite.collections.infrastructure.code.service.CodeValueReadService;
import com.favourite.collections.infrastructure.code.util.CodeModelMapper;
import com.favourite.collections.infrastructure.core.data.SearchParameters;
import com.favourite.collections.infrastructure.core.exceptions.AbstractPlatformException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CodeValueReadServiceImpl implements CodeValueReadService {
	private final CodeValueRepository codeValueRepository;
	private final CodeModelMapper codeMapper = new CodeModelMapper();

	@Override
	public Page<CodeValueData> retrieveAllCodeValues(SearchParameters searchParameters) {

		List<CodeValue> codes = codeValueRepository.findAll();
		List<CodeValueData> codeData = new ArrayList<>();
		Integer limit = searchParameters.getLimit();
		Integer offset = searchParameters.getOffset();
		String sortOrder = searchParameters.getSortOrder();
		String orderBy = searchParameters.getOrderBy();

		codes.forEach(code -> codeData.add(codeMapper.fromCodeValueToCodeValueData(code)));

		Sort.Direction direction = sortOrder.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
		PageRequest pageRequest = PageRequest.of((offset - 1), limit, Sort.by(direction, orderBy));

		final int start = offset <= 0 ? 1 : offset;
		final int end = Math.min((start + limit), codeData.size());
		return new PageImpl<>(codeData.subList(start, end), pageRequest, codeData.size());
	}

	@Override
	public ResponseEntity<CodeValueData> retrieveOneCodeValue(Long codeId) {
		CodeValue code = this.codeValueRepository.findById(codeId)
				.orElseThrow(() -> new AbstractPlatformException("error.msg.code.does.not.exist",
						"Code with id: " + codeId + " does not exist", 404));

		return ResponseEntity.ok(codeMapper.fromCodeValueToCodeValueData(code));
	}
}
