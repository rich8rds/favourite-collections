/* Collections #2024 */
package com.favourite.collections.infrastructure.useradmin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.favourite.collections.infrastructure.useradmin.domain.CodeValue;

@Repository
public interface CodeValueRepository extends JpaRepository<CodeValue, Long> {
	boolean existsByLabel(String name);

	Optional<CodeValue> findCodeValueByLabel(String name);
}
