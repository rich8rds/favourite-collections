package com.favourite.collections.infrastructure.code.service;

import com.favourite.collections.infrastructure.code.data.CodeData;
import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.core.data.SearchParameters;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface CodeReadService {
    Page<CodeData> retrieveAllCodes(SearchParameters searchParameters);

    ResponseEntity<CodeData> retrieveOneCode(Long codeId);
}
