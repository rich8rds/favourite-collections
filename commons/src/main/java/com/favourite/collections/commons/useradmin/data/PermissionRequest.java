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
