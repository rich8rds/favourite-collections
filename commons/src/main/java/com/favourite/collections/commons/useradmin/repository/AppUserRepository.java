/* Collections #2024 */
package com.favourite.collections.commons.useradmin.repository;

import java.util.Optional;

import com.favourite.collections.commons.useradmin.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	Optional<AppUser> findByEmail(String email);

	boolean existsByEmail(String email);
}
