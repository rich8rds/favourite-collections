/* Collections #2024 */
package com.favourite.collections.commons.useradmin.service;

import com.favourite.collections.commons.core.data.CommandResult;
import org.springframework.http.ResponseEntity;

import com.favourite.collections.commons.useradmin.data.PermissionRequest;
import com.favourite.collections.commons.useradmin.data.PermissionUpdate;

public interface PermissionService {
	ResponseEntity<?> getAllPermissions();

	ResponseEntity<CommandResult> createNewPermission(PermissionRequest permissionRequest);

	ResponseEntity<CommandResult> updatePermission(Long permissionId, PermissionUpdate permissionRequest);

	ResponseEntity<CommandResult> deletePermission(Long permissionId);
}
