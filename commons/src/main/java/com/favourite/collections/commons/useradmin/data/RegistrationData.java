/* Collections #2024 */
package com.favourite.collections.commons.useradmin.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationData {

	@NotBlank(message = "Field for firstname must not be empty.")
	private String firstname;

	@NotBlank(message = "Field for lastname must not be empty.")
	private String lastname;

	@Email(message = "Please enter a valid email.")
	@NotBlank(message = "Email required.")
	private String email;

	@NotBlank
	@Pattern(regexp = "(^0\\d{10}$)|(^[+]?[234]\\d{12}$)", message = "Enter a valid phone number. Should either begin with +234 or 0")
	private String phoneNumber;

	@NotBlank @Pattern(regexp = "(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
			 message = "Password must be greater than or equal to 8, "
			+ "must contain one or more uppercase characters, "
			+ "lowercase characters, numeric values and special characters ")
	private String password;

	@NotBlank
	private String confirmPassword;

	private String roleName;

	public boolean passwordsMatch() {
		return this.confirmPassword.equals(password);
	}

}
