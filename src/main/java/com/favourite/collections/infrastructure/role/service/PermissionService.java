/* Collections #2024 */
package com.favourite.collections.infrastructure.role.service;

import org.springframework.http.ResponseEntity;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.role.data.PermissionRequest;
import com.favourite.collections.infrastructure.role.data.PermissionUpdate;

public interface PermissionService {
	ResponseEntity<?> getAllPermissions();

	ResponseEntity<CommandResult> createNewPermission(PermissionRequest permissionRequest);

	ResponseEntity<CommandResult> updatePermission(Long permissionId, PermissionUpdate permissionRequest);

	ResponseEntity<CommandResult> deletePermission(Long permissionId);
}
