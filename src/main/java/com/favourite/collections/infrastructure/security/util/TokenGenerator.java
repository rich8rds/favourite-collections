/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.security.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.favourite.collections.infrastructure.security.domain.Token;

@Component
public class TokenGenerator {
	public Token generateToken(Long time, ChronoUnit timeType, Long appUserId) {
		String genToken = UUID.randomUUID().toString();

		Token token = Token.builder().token(genToken).startTime((System.currentTimeMillis() * 1000))
				.expirationTime(Instant.now().plus(time, timeType).getEpochSecond()).appUserId(appUserId).build();

		return token;
	}
}
