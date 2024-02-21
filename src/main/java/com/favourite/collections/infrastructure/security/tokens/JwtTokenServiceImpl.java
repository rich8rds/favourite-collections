/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.security.tokens;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {
	private final JwtEncoder jwtEncoder;

	@Override
	public String generateToken(Authentication authentication) {
		Instant now = Instant.now();
		String roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));

		JwtClaimsSet claims = JwtClaimsSet.builder().issuer("self").issuedAt(now)
				.expiresAt(now.plus(24, ChronoUnit.HOURS)).subject(authentication.getName()).claim("roles", roles)
				.build();
		return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

	@Override
	public String generatePasswordResetToken(String email) {
		Instant now = Instant.now();

		JwtClaimsSet claims = JwtClaimsSet.builder().issuer("self").issuedAt(now)
				.expiresAt(now.plus(15, ChronoUnit.MINUTES)).subject(email).build();
		return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

	@Override
	public String generateVerificationToken(String email) {
		Instant now = Instant.now();

		JwtClaimsSet claims = JwtClaimsSet.builder().issuer("self").issuedAt(now)
				.expiresAt(now.plus(2, ChronoUnit.MINUTES)).subject(email).build();
		return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
}
