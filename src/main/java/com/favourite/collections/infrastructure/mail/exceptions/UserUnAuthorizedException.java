/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.mail.exceptions;

import com.favourite.collections.infrastructure.core.exceptions.AbstractPlatformException;

public class UserUnAuthorizedException extends AbstractPlatformException {

	public UserUnAuthorizedException(String globalisationMessageCode, String defaultUserMessage) {
		super(globalisationMessageCode, defaultUserMessage);
	}

	protected UserUnAuthorizedException(String globalisationMessageCode, String defaultUserMessage, Throwable cause) {
		super(globalisationMessageCode, defaultUserMessage, cause);
	}

	protected UserUnAuthorizedException(String globalisationMessageCode, String defaultUserMessage,
			Object[] defaultUserMessageArgs) {
		super(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs);
	}

	public UserUnAuthorizedException(String globalisationMessageCode, String defaultUserMessage, Integer statusCode) {
		super(globalisationMessageCode, defaultUserMessage, statusCode);
	}
}
