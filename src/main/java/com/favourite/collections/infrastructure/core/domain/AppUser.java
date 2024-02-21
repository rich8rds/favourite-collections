/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.favourite.collections.infrastructure.code.domain.CodeValue;
import com.favourite.collections.infrastructure.role.domain.Role;

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
@Table(name = "t_appuser")
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id", nullable = true)
	private CodeValue gender;

	private String dateOfBirth;
	private String phoneNo;
	private Boolean isVerified = false;
	private Boolean isActive = false;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;

	// @OneToOne(fetch = FetchType.EAGER)
	// @JoinColumn(name = "cart_id")
	// private Cart cart = new Cart();
}
