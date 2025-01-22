/* Collections #2024 */
package com.favourite.collections.commons.useradmin.service;

import com.favourite.collections.commons.core.data.CommandResult;
import org.springframework.http.ResponseEntity;

import com.favourite.collections.commons.useradmin.data.CodeData;

public interface CodeWriteService {
	ResponseEntity<CommandResult> createCode(CodeData codeData);

	ResponseEntity<CommandResult> updateCode(CodeData codeData, Long codeId);

	ResponseEntity<CommandResult> deleteCode(Long codeId);

	ResponseEntity<CommandResult> assignCodeToCodeValue(Long codeId, Long codeValueId);
}
