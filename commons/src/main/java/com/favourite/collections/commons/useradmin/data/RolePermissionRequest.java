/* Collections #2024 */
package com.favourite.collections.commons.useradmin.data;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class RolePermissionRequest {
	private String roleName;
	private String permissionName;
	private Long roleId;
	private Long permissionId;
}
