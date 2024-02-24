package com.favourite.collections.infrastructure.code.service;

import com.favourite.collections.infrastructure.code.data.CodeData;
import com.favourite.collections.infrastructure.core.data.SearchParameters;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface CodeValueReadService {
    Page<CodeData> retrieveAllCodeValues(SearchParameters searchParameters);

    ResponseEntity<CodeData> retrieveOneCodeValue(Long codeId);
}
