/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.core.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.favourite.collections.infrastructure.core.domain.AppUser;
import com.favourite.collections.infrastructure.core.exceptions.AbstractPlatformException;
import com.favourite.collections.infrastructure.core.service.PlatformContextService;

@Service
public class PlatformContextServiceImpl implements PlatformContextService {
	@Override
	public AppUser authenticatedUser() {

		AppUser currentUser = null;
		final SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			final Authentication auth = context.getAuthentication();
			if (auth != null) {
				currentUser = (AppUser) auth.getPrincipal();
			}
		}

		if (currentUser == null) {
			throw new AbstractPlatformException("Unauthorized!", "Please login", HttpStatus.UNAUTHORIZED.value());
		}

		return currentUser;
	}

	@Override
	public AppUser authenticatedUserForAudit() {

		AppUser currentUser = null;
		final SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			final Authentication auth = context.getAuthentication();
			if (auth != null) {
				currentUser = (AppUser) auth.getPrincipal();
			}
		}

		return currentUser;
	}
}
