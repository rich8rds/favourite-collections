/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.security.data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationData {
	@NotBlank(message = "Field for firstname must not be empty.")
	@NotNull(message = "Firstname required.") private String firstname;

	@NotBlank(message = "Field for lastname must not be empty.")
	@NotNull(message = "Lastname required.") private String lastname;

	@Email(message = "Please enter a valid email.")
	@NotNull(message = "Email required.") private String email;

	@NotNull @Pattern(regexp = "(^0\\d{10}$)|(^[+]?[234]\\d{12}$)", message = "Enter a valid phone number. Should either begin with +234 or 0")
	private String phoneNumber;

	@NotNull @Pattern(regexp = "(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "Password must be greater than or equal to 8, "
			+ "must contain one or more uppercase characters, "
			+ "lowercase characters, numeric values and special characters ")
	private String password;

	@NotNull private String confirmPassword;

	private String roleName;

	public boolean passwordsMatch() {
		return this.confirmPassword.equals(password);
	}
}
