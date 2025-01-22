/* Collections #2024 */
package com.favourite.collections.commons.core.exceptions;

public class PlatformServiceUnavailableException extends AbstractPlatformException {

	public PlatformServiceUnavailableException(String globalisationMessageCode, String defaultUserMessage,
			Object... defaultUserMessageArgs) {
		super(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs);
	}
}
