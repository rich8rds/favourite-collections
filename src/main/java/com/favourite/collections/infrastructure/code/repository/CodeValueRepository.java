package com.favourite.collections.infrastructure.code.repository;

import com.favourite.collections.infrastructure.code.domain.CodeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeValueRepository extends JpaRepository<CodeValue, Long> {
    boolean existsByName(String name);
}
