/* Collections #2024 */
package com.favourite.collections.commons.core.config;

import java.util.Collection;
import java.util.Set;

import com.favourite.collections.commons.useradmin.domain.AppUser;
import com.favourite.collections.commons.useradmin.domain.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDetails implements UserDetails {

	private AppUser appUser;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Permission> permissions = appUser.getRole().getPermissions();
		log.info("PERMISSIONS: {}", permissions);
		return permissions;
	}

	@Override
	public String getPassword() {
		return appUser.getPassword();
	}

	@Override
	public String getUsername() {
		return appUser.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return appUser.isActive();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return appUser.isVerified();
	}
}
