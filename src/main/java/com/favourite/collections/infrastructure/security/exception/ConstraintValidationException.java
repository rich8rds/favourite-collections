/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.security.exception;

import com.favourite.collections.infrastructure.core.exceptions.AbstractPlatformException;

public class ConstraintValidationException extends AbstractPlatformException {
	public ConstraintValidationException(String globalisationMessageCode, String defaultUserMessage) {
		super(globalisationMessageCode, defaultUserMessage);
	}

	protected ConstraintValidationException(String globalisationMessageCode, String defaultUserMessage,
			Throwable cause) {
		super(globalisationMessageCode, defaultUserMessage, cause);
	}

	protected ConstraintValidationException(String globalisationMessageCode, String defaultUserMessage,
			Object[] defaultUserMessageArgs) {
		super(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs);
	}

	public ConstraintValidationException(String globalisationMessageCode, String defaultUserMessage,
			Integer statusCode) {
		super(globalisationMessageCode, defaultUserMessage, statusCode);
	}
}
