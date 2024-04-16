/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.code.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.favourite.collections.infrastructure.code.data.CodeData;
import com.favourite.collections.infrastructure.core.data.SearchParameters;

public interface CodeReadService {
	Page<CodeData> retrieveAllCodes(SearchParameters searchParameters);

	ResponseEntity<CodeData> retrieveOneCode(Long codeId);
}
