/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.role.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class PermissionRequest {
	@NotBlank(message = "Grouping cannot be blank.")
	@NotNull(message = "Grouping cannot be blank.") private String grouping;

	@NotBlank(message = "Action name cannot be blank.")
	@NotNull(message = "Action name cannot be null.") private String actionName;

	@NotBlank(message = "Entity name cannot be blank.")
	@NotNull(message = "Action name cannot be null.") private String entityName;

	private String description;
	private Boolean isDisabled;
}
