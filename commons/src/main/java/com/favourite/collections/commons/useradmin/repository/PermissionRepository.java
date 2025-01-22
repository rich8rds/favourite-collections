/* Collections #2024 */
package com.favourite.collections.commons.useradmin.repository;

import java.util.Optional;

import com.favourite.collections.commons.useradmin.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	Optional<Permission> findByDisplayName(String displayName);

	boolean existsByDisplayName(String displayName);
}
