/* Collections #2024 */
package com.favourite.collections.infrastructure.useradmin.service;

import org.springframework.http.ResponseEntity;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.useradmin.data.CodeData;

public interface CodeWriteService {
	ResponseEntity<CommandResult> createCode(CodeData codeData);

	ResponseEntity<CommandResult> updateCode(CodeData codeData, Long codeId);

	ResponseEntity<CommandResult> deleteCode(Long codeId);

	ResponseEntity<CommandResult> assignCodeToCodeValue(Long codeId, Long codeValueId);
}
