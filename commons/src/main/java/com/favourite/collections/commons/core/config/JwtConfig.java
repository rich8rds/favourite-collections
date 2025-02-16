/* Collections #2024 */
package com.favourite.collections.commons.core.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;

import com.favourite.collections.commons.core.exceptions.AbstractPlatformException;
import com.favourite.collections.commons.core.service.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Slf4j
@Component
public class JwtConfig {

	@Value("${app.jwt.secret.key}")
	private String SECRET_KEY;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public String getLoggedInUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth == null ? "" : auth.getName();
	}

	public boolean isCurrentUser(String userEmail) {
		if (userEmail == null) {
			return true;
		} else
			return Objects.equals(userEmail, getLoggedInUsername());
	}

	public void validateCurrentUser(String userEmail) {
		if (!isCurrentUser(userEmail)) {
			throw new AbstractPlatformException(ResponseCodeEnum.INVALID_TOKEN.name(), ResponseCodeEnum.INVALID_TOKEN);
		}
	}

	private SecretKey getSigningKey() {
		byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyByte);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(Authentication authentication) {
		Map<String, Object> claims = new HashMap<>();
		log.info("authenticated user: {}", authentication.getPrincipal());
		log.info("authentication: {}", authentication);
		claims.put("role", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(",")));

		AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
		claims.put("email", userDetails.getUsername());

		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		Instant now = Instant.now();

		return Jwts.builder().setIssuer("favourite-collections")
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(15, ChronoUnit.MINUTES)))
				.signWith(getSigningKey())
				.compact();
	}

	public Boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public String generatePasswordResetToken(String email) {
		return createToken(new HashMap<>(), email);
	}

	public String generateVerificationToken(String email) {
		Instant now = Instant.now();

		return Jwts.builder().setClaims(new HashMap<>())
				.setSubject(email)
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(2, ChronoUnit.MINUTES)))
				.signWith(getSigningKey())
				.compact();
	}
}
