/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.core.exceptions;

public class PlatformServiceUnavailableException extends AbstractPlatformException {

	public PlatformServiceUnavailableException(String globalisationMessageCode, String defaultUserMessage,
			Object... defaultUserMessageArgs) {
		super(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs);
	}
}
