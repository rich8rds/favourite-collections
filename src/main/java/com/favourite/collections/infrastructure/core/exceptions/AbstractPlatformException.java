/* Collections #2024 */
package com.favourite.collections.infrastructure.core.exceptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.favourite.collections.infrastructure.core.data.ApiSubError;
import com.favourite.collections.infrastructure.core.service.ResponseCodeEnum;

import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class AbstractPlatformException extends RuntimeException {
	private static final Object[] NO_ARGS = new Object[0];
	private final String globalisationMessageCode;
	private final String defaultUserMessage;
	private final Object[] defaultUserMessageArgs;
	private final Integer statusCode;
	private final Collection<ApiSubError> subErrors;

	public AbstractPlatformException(String globalisationMessageCode, ResponseCodeEnum responseCodeEnum) {
		super(responseCodeEnum.getValue());
		this.globalisationMessageCode = globalisationMessageCode;
		this.defaultUserMessage = null;
		this.defaultUserMessageArgs = NO_ARGS;
		this.statusCode = responseCodeEnum.getCode();
		this.subErrors = Collections.emptyList();
	}

	public AbstractPlatformException(String globalisationMessageCode, String defaultUserMessage) {
		super(defaultUserMessage);
		this.globalisationMessageCode = globalisationMessageCode;
		this.defaultUserMessage = defaultUserMessage;
		this.defaultUserMessageArgs = NO_ARGS;
		this.statusCode = null;
		this.subErrors = Collections.emptyList();
	}

	protected AbstractPlatformException(String globalisationMessageCode, String defaultUserMessage, Throwable cause) {
		super(defaultUserMessage, cause);
		this.globalisationMessageCode = globalisationMessageCode;
		this.defaultUserMessage = defaultUserMessage;
		this.defaultUserMessageArgs = NO_ARGS;
		this.statusCode = null;
		this.subErrors = Collections.emptyList();
	}

	protected AbstractPlatformException(String globalisationMessageCode, String defaultUserMessage,
			Object[] defaultUserMessageArgs) {
		super(defaultUserMessage, findThrowableCause(defaultUserMessageArgs));
		this.globalisationMessageCode = globalisationMessageCode;
		this.defaultUserMessage = defaultUserMessage;
		this.defaultUserMessageArgs = AbstractPlatformException.filterThrowableCause(defaultUserMessageArgs);
		this.statusCode = null;
		this.subErrors = Collections.emptyList();
	}

	public AbstractPlatformException(String globalisationMessageCode, String defaultUserMessage, Integer statusCode) {
		this.globalisationMessageCode = globalisationMessageCode;
		this.defaultUserMessage = defaultUserMessage;
		this.statusCode = statusCode;
		this.defaultUserMessageArgs = null;
		this.subErrors = Collections.emptyList();
	}

	public AbstractPlatformException(String globalisationMessageCode, String defaultUserMessage, Integer statusCode,
			Collection<ApiSubError> subErrors) {
		this.globalisationMessageCode = globalisationMessageCode;
		this.defaultUserMessage = defaultUserMessage;
		this.statusCode = statusCode;
		this.defaultUserMessageArgs = null;
		this.subErrors = subErrors;
	}

	private static Throwable findThrowableCause(Object[] defaultUserMessageArgs) {
		for (Object defaultUserMessageArg : defaultUserMessageArgs) {
			if (defaultUserMessageArg instanceof Throwable) {
				return (Throwable) defaultUserMessageArg;
			}
		}
		return null;
	}

	private static Object[] filterThrowableCause(Object[] defaultUserMessageArgs) {
		List<Object> filteredDefaultUserMessageArgs = new ArrayList<>(defaultUserMessageArgs.length);
		for (Object defaultUserMessageArg : defaultUserMessageArgs) {
			if (!(defaultUserMessageArg instanceof Throwable)) {
				filteredDefaultUserMessageArgs.add(defaultUserMessageArg);
			}
		}
		return filteredDefaultUserMessageArgs.toArray();
	}
}
