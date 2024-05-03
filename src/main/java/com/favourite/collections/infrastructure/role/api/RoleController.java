/* Collections #2024 */
package com.favourite.collections.infrastructure.role.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.role.data.RolePermissionRequest;
import com.favourite.collections.infrastructure.role.data.RoleRequest;
import com.favourite.collections.infrastructure.role.service.RoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Roles")
@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
	private final RoleService roleService;

	@GetMapping
	public ResponseEntity<?> getAllRoles() {
		return roleService.getAllRoles();
	}

	@PostMapping
	public ResponseEntity<CommandResult> createRole(@RequestBody @Valid RoleRequest roleRequest) {
		return this.roleService.createNewRole(roleRequest);
	}

	@PutMapping("/{roleId}")
	public ResponseEntity<CommandResult> updateRole(@PathVariable Long roleId,
			@RequestBody @Valid RoleRequest roleRequest) {
		return this.roleService.updateRole(roleId, roleRequest);
	}

	@PutMapping("/{roleId}/assign")
	@Operation(summary = "You can assign a permission to a role")
	public ResponseEntity<CommandResult> tieRoleToPermission(@RequestBody RolePermissionRequest rolePermissionRequest) {
		return this.roleService.tieRoleToPermission(rolePermissionRequest);
	}

	@DeleteMapping("/{roleId}")
	public ResponseEntity<CommandResult> deleteRole(@PathVariable Long roleId) {
		return this.roleService.deleteRole(roleId);
	}
}
