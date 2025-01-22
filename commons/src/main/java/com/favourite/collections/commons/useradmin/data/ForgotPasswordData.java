/* Collections #2024 */
package com.favourite.collections.commons.useradmin.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ForgotPasswordData {
	@NotBlank(message = "Email is required")
	@NotNull(message = "Email cannot be null.") @Email
	private String email;
}
