/* Collections #2024 */
package com.favourite.collections.infrastructure.core.data;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ApiError {

	private final HttpStatus status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private final LocalDateTime timestamp = LocalDateTime.now();

	private final String message;
	private final String globalMessageCode;
	private final String debugMessage;
	private final Collection<ApiSubError> subErrors;
}
