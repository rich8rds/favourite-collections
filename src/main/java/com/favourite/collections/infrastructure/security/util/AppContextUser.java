/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.security.util;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.favourite.collections.infrastructure.core.domain.AppUser;
import com.favourite.collections.infrastructure.core.exceptions.AbstractPlatformException;
import com.favourite.collections.infrastructure.core.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AppContextUser {
	private final AppUserRepository appUserRepository;

	@Bean
	public Optional<String> extractEmailFromPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("AUTHENTICATION: {}", authentication);
		if (!(authentication instanceof AnonymousAuthenticationToken))
			return Optional.of(authentication.getName());
		return Optional.empty();
	}

	public AppUser authenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		AppUser appUser = appUserRepository.findByEmail(authentication.getName()).orElseThrow(
				() -> new AbstractPlatformException("error.authentication.not.found", "No user with this email", 404));
		return appUser;
	}
}
