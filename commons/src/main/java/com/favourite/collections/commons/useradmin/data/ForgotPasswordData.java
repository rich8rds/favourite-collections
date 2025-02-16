/* Collections #2024 */
package com.favourite.collections.commons.useradmin.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordData {

	@NotBlank(message = "Email is required")
	@Email
	private String email;

}
