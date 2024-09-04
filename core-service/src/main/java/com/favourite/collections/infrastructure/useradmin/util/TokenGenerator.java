/* Collections #2024 */
package com.favourite.collections.infrastructure.useradmin.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.favourite.collections.infrastructure.useradmin.domain.Token;

@Component
public class TokenGenerator {
	public Token generateToken(Long time, ChronoUnit chronoUnit, Long appUserId) {
		String genToken = UUID.randomUUID().toString();

		return Token.builder().token(genToken).startTime((System.currentTimeMillis() * 1000))
				.expirationTime(Instant.now().plus(time, chronoUnit).getEpochSecond()).appUserId(appUserId).build();
	}
}
