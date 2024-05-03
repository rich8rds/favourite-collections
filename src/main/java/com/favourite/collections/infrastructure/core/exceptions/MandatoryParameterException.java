/* Collections #2024 */
package com.favourite.collections.infrastructure.core.exceptions;

import java.util.List;

/**
 * @author Olakunle.Thompson A {@link RuntimeException} that is thrown in the
 *         case where invalid parameters are sent in the body of the request to
 *         the platform api.
 */
public class MandatoryParameterException extends RuntimeException {

	private final List<String> mandatoryParameters;

	public MandatoryParameterException(final List<String> mandatoryParameters) {
		this.mandatoryParameters = mandatoryParameters;
	}

	public List<String> getUnsupportedParameters() {
		return this.mandatoryParameters;
	}
}
