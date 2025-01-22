/* Collections #2024 */
package com.favourite.collections.commons.core.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiValidationError extends ApiSubError {
	private Object object;
	private String field;
	private Object rejectedValue;
	private String message;

	public ApiValidationError(Object object, String message) {
		this.object = object;
		this.message = message;
	}
}
