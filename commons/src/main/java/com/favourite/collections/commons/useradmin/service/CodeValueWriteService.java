/* Collections #2024 */
package com.favourite.collections.commons.useradmin.service;

import com.favourite.collections.commons.core.data.CommandResult;
import com.favourite.collections.commons.useradmin.data.CodeValueData;
import org.springframework.http.ResponseEntity;


public interface CodeValueWriteService {
	ResponseEntity<CommandResult> createCodeValue(CodeValueData codeData);

	ResponseEntity<CommandResult> updateCodeValue(CodeValueData codeData, Long codeValueId);

	ResponseEntity<CommandResult> deleteCodeValue(Long codeValueId);
}
