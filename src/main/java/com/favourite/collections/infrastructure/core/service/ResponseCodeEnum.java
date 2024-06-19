/* Collections #2024 */
package com.favourite.collections.infrastructure.core.service;

public enum ResponseCodeEnum {
	BAD_REQUEST(400, "BAD_REQUEST"), PROCESSING(1, "PROCESSING"), EXCEPTION(2, "EXCEPTION"), USER_NOT_FOUND(404,
			"User could not be found."), INVALID_TOKEN(400, "This is an invalid token."), ROLE_ALREADY_EXISTS(409, "Role already exists."),;

	private final Integer code;
	private final String value;

	ResponseCodeEnum(Integer code, String value) {
		this.code = code;
		this.value = value;
	}

	public Integer getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}
}
