/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.core.data;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.favourite.collections.infrastructure.core.domain.AppUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDetails implements UserDetails {

	private AppUser appUser;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
		// return appUser.getRole().getGrantedAuthorities();
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
		Boolean isActive = appUser.getIsActive();
		return isActive != null && isActive;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		Boolean isVerified = appUser.getIsVerified();
		return isVerified != null && isVerified;
	}
}
