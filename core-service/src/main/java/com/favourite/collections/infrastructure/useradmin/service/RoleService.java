/* Collections #2024 */
package com.favourite.collections.infrastructure.useradmin.service;

import org.springframework.http.ResponseEntity;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.useradmin.data.RolePermissionRequest;
import com.favourite.collections.infrastructure.useradmin.data.RoleRequest;

public interface RoleService {
	ResponseEntity<?> getAllRoles();

	ResponseEntity<CommandResult> createNewRole(RoleRequest roleRequest);

	ResponseEntity<CommandResult> tieRoleToPermission(RolePermissionRequest rolePermissionRequest);

	ResponseEntity<CommandResult> updateRole(Long roleId, RoleRequest roleRequest);

	ResponseEntity<CommandResult> deleteRole(Long roleId);
}
