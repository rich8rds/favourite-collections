/* Collections #2024 */
package com.favourite.collections.commons.useradmin.domain;

import com.favourite.collections.commons.core.domain.AbstractAuditableCustom;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "m_appuser")
public class AppUser extends AbstractAuditableCustom {

	@NotNull(message = "First name cannot be missing or empty") @Column(nullable = false, length = 50)
	private String firstname;

	@NotNull(message = "Last name cannot be missing or empty") @Column(nullable = false, length = 50)
	private String lastname;

	@Email(message = "Email must be valid!")
	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	private String dateOfBirth;
	private String phoneNo;
	private boolean isVerified;
	private boolean isActive;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id", nullable = true)
	private CodeValue gender;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cart_id")
	private Cart cart = new Cart();

	public Cart getCart() {
		if (cart == null) {
			this.cart = new Cart();
		}
		return this.cart;
	}
}
