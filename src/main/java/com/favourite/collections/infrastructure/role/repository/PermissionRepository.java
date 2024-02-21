/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.role.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.favourite.collections.infrastructure.role.domain.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	Optional<Permission> findByDisplayName(String displayName);

	boolean existsByDisplayName(String displayName);
}
