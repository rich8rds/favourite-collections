/* Collections #2024 */
package com.favourite.collections.infrastructure.security.util;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.favourite.collections.infrastructure.core.exceptions.AbstractPlatformException;
import com.favourite.collections.infrastructure.core.service.ResponseCodeEnum;
import com.favourite.collections.infrastructure.security.repository.AppUserRepository;
import com.favourite.collections.infrastructure.security.service.AppUserDetailsService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final AppUserRepository appUserRepository;
	private final AppUserDetailsService appUserDetailsService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		final String authHeader = request.getHeader(AUTHORIZATION);
		final String userEmail;
		final String jwtToken;

		if (authHeader == null || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}

		jwtToken = authHeader.substring(7);
		userEmail = this.jwtUtil.extractUsername(jwtToken);
		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails user = this.appUserDetailsService.loadUserByUsername(userEmail);
			if (user == null) {
				throw new AbstractPlatformException(ResponseCodeEnum.INVALID_TOKEN.name(),
						ResponseCodeEnum.USER_NOT_FOUND);
			}

			if (jwtUtil.isTokenValid(jwtToken, user)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
						user.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
