/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.security.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdatePasswordData {
	@NotBlank(message = "This field is required")
	private String oldPassword;

	@NotNull(message = "Password field cannot be null") @NotBlank(message = "Password field required.")
	@Pattern(regexp = "(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "Password must be greater than or equal to 8, "
			+ "must contain one or more uppercase characters, "
			+ "lowercase characters, numeric values and special characters ")
	private String newPassword;

	@NotNull(message = "Password field cannot be null") @NotBlank(message = "Password field required.")
	private String confirmNewPassword;

	public boolean passwordsMatch() {
		return this.confirmNewPassword.equals(newPassword);
	}
}
