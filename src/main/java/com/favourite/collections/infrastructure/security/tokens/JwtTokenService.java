/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.security.tokens;

import org.springframework.security.core.Authentication;

public interface JwtTokenService {
	String generateToken(Authentication authentication);

	String generatePasswordResetToken(String email);

	String generateVerificationToken(String email);
}
