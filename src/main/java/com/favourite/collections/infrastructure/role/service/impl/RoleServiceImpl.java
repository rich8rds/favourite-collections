/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.role.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.core.data.CommandResultBuilder;
import com.favourite.collections.infrastructure.core.exceptions.AbstractPlatformException;
import com.favourite.collections.infrastructure.role.data.RolePermissionRequest;
import com.favourite.collections.infrastructure.role.data.RoleRequest;
import com.favourite.collections.infrastructure.role.domain.Permission;
import com.favourite.collections.infrastructure.role.domain.Role;
import com.favourite.collections.infrastructure.role.repository.PermissionRepository;
import com.favourite.collections.infrastructure.role.repository.RoleRepository;
import com.favourite.collections.infrastructure.role.service.RoleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
	private final RoleRepository roleRepository;
	private final PermissionRepository permissionRepository;

	@Override
	public ResponseEntity<?> getAllRoles() {
		return ResponseEntity.status(200).body(roleRepository.findAll());
	}

	@Override
	public ResponseEntity<CommandResult> createNewRole(RoleRequest roleRequest) {
		String name = roleRequest.getName();
		String description = roleRequest.getDescription();
		boolean isDisabled = roleRequest.getIsDisabled();

		Role role = Role.builder().name(name).description(description).isDisabled(isDisabled).build();
		roleRepository.saveAndFlush(role);
		return ResponseEntity.status(201)
				.body(new CommandResultBuilder().message("Role with '" + name + "' created!").build());
	}

	@Override
	public ResponseEntity<CommandResult> tieRoleToPermission(RolePermissionRequest rolePermissionRequest) {
		if (rolePermissionRequest == null)
			return ResponseEntity.status(400).body(new CommandResultBuilder()
					.message("Enter valid inputs:roleName or roleId and " + "permissionName or permissionId required")
					.response("Request must not be null!").build());

		String roleName = rolePermissionRequest.getRoleName();
		String permissionName = rolePermissionRequest.getPermissionName();

		Long roleId = rolePermissionRequest.getRoleId();
		Long permissionId = rolePermissionRequest.getPermissionId();

		Role role = null;
		if (roleName != null) {
			role = roleRepository.findByName(roleName).orElse(null);
		}
		if (role == null && roleId != null) {
			role = roleRepository.findById(roleId).orElse(null);
		}
		if (role == null)
			throw new IllegalArgumentException("Role with name " + roleName + " does not exist");

		Permission permission = null;
		if (permissionName != null) {
			permission = permissionRepository.findByDisplayName(permissionName).orElse(null);
		}
		if (permission == null && permissionId != null) {
			permission = permissionRepository.findById(permissionId).orElse(null);
		}
		if (permission == null)
			throw new IllegalArgumentException("Permission does not exist");

		role.getPermissions().add(permission);
		roleRepository.save(role);

		return ResponseEntity.status(200).body(new CommandResultBuilder().resourceId(String.valueOf(roleId))
				.entityId(permission.getId()).message("Invalid role name")
				.response("Role with name '" + roleName + "' has permission with name '" + permissionName + "' added.")
				.build());
	}

	@Override
	public ResponseEntity<CommandResult> updateRole(Long roleId, RoleRequest roleRequest) {
		String name = roleRequest.getName();
		String description = roleRequest.getDescription();
		Boolean isDisabled = roleRequest.getIsDisabled();

		Role role = this.roleRepository.findById(roleId)
				.orElseThrow(() -> new AbstractPlatformException("Role with id: " + roleId + " not found",
						String.valueOf(roleId), 404));

		Map<String, Object> changes = new HashMap<>();
		if (name != null) {
			role.setName(name);
			changes.put("name", name);
		}

		if (description != null) {
			role.setDescription(description);
			changes.put("description", description);
		}

		if (isDisabled != null) {
			role.setIsDisabled(isDisabled);
			changes.put("isDisabled", isDisabled);
		}

		roleRepository.saveAndFlush(role);
		return ResponseEntity.status(200)
				.body(new CommandResultBuilder().message("Role with '" + name + "' created!").changes(changes).build());
	}

	@Override
	public ResponseEntity<CommandResult> deleteRole(Long roleId) {
		Role role = this.roleRepository.findById(roleId)
				.orElseThrow(() -> new AbstractPlatformException("Role with id: " + roleId + " not found",
						String.valueOf(roleId), 404));
		String roleName = role.getName();
		this.roleRepository.delete(role);

		return ResponseEntity.status(200).body(new CommandResultBuilder()
				.message("Role with name " + roleName + " deleted!").entityId(roleId).build());
	}
}
