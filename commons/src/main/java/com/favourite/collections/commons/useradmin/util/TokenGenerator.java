/* Collections #2024 */
package com.favourite.collections.commons.useradmin.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.favourite.collections.commons.useradmin.domain.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenGenerator {
	public Token generateToken(Long time, ChronoUnit chronoUnit, Long appUserId) {
		String genToken = UUID.randomUUID().toString();

		log.info("Generated token: {}", genToken);
		return Token.builder().token(genToken).startTime((System.currentTimeMillis() * 1000))
				.expirationTime(Instant.now().plus(time, chronoUnit).getEpochSecond()).appUserId(appUserId).build();
	}
}
