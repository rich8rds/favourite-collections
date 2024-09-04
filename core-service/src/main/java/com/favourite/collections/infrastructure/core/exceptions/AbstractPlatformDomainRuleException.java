/* Collections #2024 */
package com.favourite.collections.infrastructure.core.exceptions;

/**
 * A {@link RuntimeException} thrown when a valid API request ends up violating
 * some domain rule.
 */
public abstract class AbstractPlatformDomainRuleException extends AbstractPlatformException {

	protected AbstractPlatformDomainRuleException(String globalisationMessageCode, String defaultUserMessage,
			Object... defaultUserMessageArgs) {
		super(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs);
	}
}
