/* Collections #2024 */
package com.favourite.collections.commons.core.data;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public final class ApiError {

	private final HttpStatus status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private final String timestamp = LocalDateTime.now().toString();

	private final String message;
	private final String path;
	private final String globalMessageCode;
	private final String debugMessage;
	private final Collection<ApiSubError> subErrors;

	public ApiError(String message, Collection<ApiSubError> subErrors) {
		this.message = message;
		this.subErrors = subErrors;
		this.globalMessageCode = null;
		this.debugMessage = null;
		this.status = HttpStatus.BAD_REQUEST;
		this.path = null;
	}


}
