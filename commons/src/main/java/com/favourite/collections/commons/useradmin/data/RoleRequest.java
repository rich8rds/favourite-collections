/* Collections #2024 */
package com.favourite.collections.commons.useradmin.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleRequest {
	@NotBlank(message = "Role name cannot be blank.")
	@NotNull(message = "Role name cannot be null.") private String name;

	@NotBlank(message = "Role description cannot be blank.")
	@NotNull(message = "Role description cannot be null.") private String description;

	@NotNull(message = "isDisabled cannot be null") private Boolean isDisabled;
}
