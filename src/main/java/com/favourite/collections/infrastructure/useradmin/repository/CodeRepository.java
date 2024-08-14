/* Collections #2024 */
package com.favourite.collections.infrastructure.useradmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.favourite.collections.infrastructure.useradmin.domain.Code;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
	boolean existsByName(String name);
}
