/* Collections #2024 */
package com.favourite.collections.infrastructure.useradmin.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.favourite.collections.infrastructure.core.data.SearchParameters;
import com.favourite.collections.infrastructure.useradmin.data.CodeValueData;

public interface CodeValueReadService {
	Page<CodeValueData> retrieveAllCodeValues(SearchParameters searchParameters);

	ResponseEntity<CodeValueData> retrieveOneCodeValue(Long codeId);
}
