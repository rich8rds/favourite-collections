/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.role.data;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class PermissionUpdate {
	private String grouping;
	private String actionName;
	private String entityName;
	private String description;
	private Boolean isDisabled;
}
