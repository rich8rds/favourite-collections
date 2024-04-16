/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.role.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.core.data.CommandResultBuilder;
import com.favourite.collections.infrastructure.core.exceptions.AbstractPlatformException;
import com.favourite.collections.infrastructure.role.data.PermissionRequest;
import com.favourite.collections.infrastructure.role.data.PermissionUpdate;
import com.favourite.collections.infrastructure.role.domain.Permission;
import com.favourite.collections.infrastructure.role.repository.PermissionRepository;
import com.favourite.collections.infrastructure.role.service.PermissionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
	private final PermissionRepository permissionRepository;

	@Override
	public ResponseEntity<?> getAllPermissions() {
		return ResponseEntity.status(200).body(permissionRepository.findAll());
	}

	@Override
	public ResponseEntity<CommandResult> createNewPermission(PermissionRequest permissionRequest) {
		String grouping = permissionRequest.getGrouping();
		String actionName = permissionRequest.getActionName();
		String entityName = permissionRequest.getEntityName();
		String description = permissionRequest.getDescription();
		boolean isDisabled = permissionRequest.getIsDisabled();
		String displayName = actionName + "_" + entityName;

		if (permissionRepository.existsByDisplayName(displayName))
			throw new IllegalArgumentException("Permission already exists");

		Permission permission = Permission.builder().grouping(grouping).actionName(actionName).entityName(entityName)
				.displayName(displayName).description(description).isDisabled(isDisabled).build();
		permissionRepository.saveAndFlush(permission);
		return ResponseEntity.status(201).body(new CommandResultBuilder().entityId(permission.getId())
				.message("Permission created").response("Permission with name '" + actionName + "' created!").build());
	}

	@Override
	public ResponseEntity<CommandResult> updatePermission(Long permissionId, PermissionUpdate permissionUpdate) {
		String grouping = permissionUpdate.getGrouping();
		String actionName = permissionUpdate.getActionName();
		String entityName = permissionUpdate.getEntityName();
		String description = permissionUpdate.getDescription();
		Boolean isDisabled = permissionUpdate.getIsDisabled();

		Permission permission = this.permissionRepository.findById(permissionId)
				.orElseThrow(() -> new AbstractPlatformException("Permission with id: " + permissionId + " not found",
						String.valueOf(permissionId), 404));

		Map<String, Object> changes = new HashMap<>();

		if (grouping != null) {
			permission.setGrouping(grouping);
			changes.put("grouping", grouping);
		}

		if (actionName != null) {
			permission.setGrouping(actionName);
			changes.put("actionName", actionName);
		}

		if (entityName != null) {
			String displayName = actionName + "_" + entityName;
			if (permissionRepository.existsByDisplayName(displayName)) {
				throw new AbstractPlatformException("Permission already exists!", "Create a unique entityName!");
			}
			permission.setGrouping(entityName);
			changes.put("entityName", entityName);
		}

		if (description != null) {
			permission.setDescription(description);
			changes.put("description", description);
		}

		if (isDisabled != null) {
			permission.setIsDisabled(isDisabled);
			changes.put("isDisabled", isDisabled);
		}

		permissionRepository.saveAndFlush(permission);
		return ResponseEntity.status(200).body(new CommandResultBuilder()
				.message("Permission with '" + grouping + "' updated!").changes(changes).build());
	}

	@Override
	public ResponseEntity<CommandResult> deletePermission(Long permissionId) {
		Permission permission = this.permissionRepository.findById(permissionId)
				.orElseThrow(() -> new AbstractPlatformException("Permission with id: " + permissionId + " not found",
						String.valueOf(permissionId), 404));
		String permissionName = permission.getDisplayName();
		this.permissionRepository.delete(permission);

		return ResponseEntity.status(200)
				.body(new CommandResultBuilder().message("Permission with display name " + permissionName + " deleted!")
						.entityId(permissionId).build());
	}
}
