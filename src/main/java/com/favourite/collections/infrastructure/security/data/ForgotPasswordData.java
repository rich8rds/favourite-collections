/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.security.data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ForgotPasswordData {
	@NotBlank(message = "Email is required")
	@NotNull(message = "Email cannot be null.") @Email
	private String email;
}
