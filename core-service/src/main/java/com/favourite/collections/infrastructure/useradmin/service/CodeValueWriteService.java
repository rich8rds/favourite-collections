/* Collections #2024 */
package com.favourite.collections.infrastructure.useradmin.service;

import org.springframework.http.ResponseEntity;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.useradmin.data.CodeValueData;

public interface CodeValueWriteService {
	ResponseEntity<CommandResult> createCodeValue(CodeValueData codeData);

	ResponseEntity<CommandResult> updateCodeValue(CodeValueData codeData, Long codeValueId);

	ResponseEntity<CommandResult> deleteCodeValue(Long codeValueId);
}
