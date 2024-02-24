package com.favourite.collections.infrastructure.code.service;

import com.favourite.collections.infrastructure.code.data.CodeValueData;
import com.favourite.collections.infrastructure.core.data.CommandResult;
import org.springframework.http.ResponseEntity;

public interface CodeValueWriteService {
    ResponseEntity<CommandResult> createCodeValue(CodeValueData codeData);

    ResponseEntity<CommandResult> updateCodeValue(CodeValueData codeData, Long codeValueId);

    ResponseEntity<CommandResult> deleteCodeValue(Long codeValueId);
}
