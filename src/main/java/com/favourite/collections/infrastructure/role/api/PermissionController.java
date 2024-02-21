/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.role.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.role.data.PermissionRequest;
import com.favourite.collections.infrastructure.role.data.PermissionUpdate;
import com.favourite.collections.infrastructure.role.service.PermissionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Permissions")
@RestController
@RequiredArgsConstructor
@RequestMapping("/permissions")
public class PermissionController {
	private final PermissionService permissionService;

	@GetMapping("/permissions")
	public ResponseEntity<?> getAllPermissions() {
		return permissionService.getAllPermissions();
	}

	@PostMapping("/permissions")
	public ResponseEntity<CommandResult> createPermissions(@RequestBody PermissionRequest permissionRequest) {
		return permissionService.createNewPermission(permissionRequest);
	}

	@PutMapping("/{permissionId}")
	public ResponseEntity<CommandResult> updatePermission(@PathVariable Long permissionId,
			@RequestBody PermissionUpdate permissionUpdate) {
		return permissionService.updatePermission(permissionId, permissionUpdate);
	}

	@DeleteMapping("/{permissionId}")
	public ResponseEntity<CommandResult> deletePermission(@PathVariable Long permissionId) {
		return permissionService.deletePermission(permissionId);
	}
}
