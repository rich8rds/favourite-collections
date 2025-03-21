/* Collections #2025 */
package com.favourite.collections.commons.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class GeneralSecurityConfig {

	@Bean
	public SecurityWebFilterChain generalSecurityFilterChain(ServerHttpSecurity http) {

		http.csrf(ServerHttpSecurity.CsrfSpec::disable).cors(Customizer.withDefaults())
				.authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
						.anyExchange().permitAll());
		return http.build();
	}
}
