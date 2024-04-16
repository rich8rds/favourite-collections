/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.core.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.favourite.collections.infrastructure.core.data.AppUserDetails;
import com.favourite.collections.infrastructure.core.domain.AppUser;
import com.favourite.collections.infrastructure.core.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
	private final AppUserRepository personRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser dbUser = personRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Not Found"));
		return new AppUserDetails(dbUser);
	}
}
