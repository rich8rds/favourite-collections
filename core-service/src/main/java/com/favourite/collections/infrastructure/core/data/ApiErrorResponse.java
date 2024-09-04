/* Collections #2024 */
package com.favourite.collections.infrastructure.core.data;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ApiErrorResponse<T> {
	private LocalDateTime timestamp;
	private String clientMessage;
	private String developerMessage;
	private Integer status;
	private T exception;
	private String path;
	private Map<String, Object> params;
}
