/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.code.service;

import org.springframework.http.ResponseEntity;

import com.favourite.collections.infrastructure.code.data.CodeData;
import com.favourite.collections.infrastructure.core.data.CommandResult;

public interface CodeWriteService {
	ResponseEntity<CommandResult> createCode(CodeData codeData);

	ResponseEntity<CommandResult> updateCode(CodeData codeData, Long codeId);

	ResponseEntity<CommandResult> deleteCode(Long codeId);

	ResponseEntity<CommandResult> assignCodeToCodeValue(Long codeId, Long codeValueId);
}
