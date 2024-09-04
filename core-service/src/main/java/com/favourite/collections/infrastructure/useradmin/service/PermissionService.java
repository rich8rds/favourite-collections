/* Collections #2024 */
package com.favourite.collections.infrastructure.useradmin.service;

import org.springframework.http.ResponseEntity;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.useradmin.data.PermissionRequest;
import com.favourite.collections.infrastructure.useradmin.data.PermissionUpdate;

public interface PermissionService {
	ResponseEntity<?> getAllPermissions();

	ResponseEntity<CommandResult> createNewPermission(PermissionRequest permissionRequest);

	ResponseEntity<CommandResult> updatePermission(Long permissionId, PermissionUpdate permissionRequest);

	ResponseEntity<CommandResult> deletePermission(Long permissionId);
}
