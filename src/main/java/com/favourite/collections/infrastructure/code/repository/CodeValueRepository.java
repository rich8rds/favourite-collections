/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.favourite.collections.infrastructure.code.domain.CodeValue;

@Repository
public interface CodeValueRepository extends JpaRepository<CodeValue, Long> {
	boolean existsByLabel(String name);
}
