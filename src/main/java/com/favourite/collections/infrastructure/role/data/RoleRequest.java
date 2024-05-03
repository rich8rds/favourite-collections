/* Collections #2024 */
package com.favourite.collections.infrastructure.role.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class RoleRequest {
	@NotBlank(message = "Role name cannot be blank.")
	@NotNull(message = "Role name cannot be null.") private String name;

	@NotBlank(message = "Role description cannot be blank.")
	@NotNull(message = "Role description cannot be null.") private String description;

	@NotNull(message = "isDisabled cannot be null") private Boolean isDisabled;
}
