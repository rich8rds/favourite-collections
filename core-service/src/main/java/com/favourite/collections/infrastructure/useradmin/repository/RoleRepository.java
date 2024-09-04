/* Collections #2024 */
package com.favourite.collections.infrastructure.useradmin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.favourite.collections.infrastructure.useradmin.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	boolean existsByName(String name);

	Optional<Role> findByName(String user);

	Optional<Role> findByNameAndAndIsDisabled(String name, boolean isDisabled);
}
