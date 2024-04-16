/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.code.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.favourite.collections.infrastructure.code.data.CodeValueData;
import com.favourite.collections.infrastructure.core.data.SearchParameters;

public interface CodeValueReadService {
	Page<CodeValueData> retrieveAllCodeValues(SearchParameters searchParameters);

	ResponseEntity<CodeValueData> retrieveOneCodeValue(Long codeId);
}
