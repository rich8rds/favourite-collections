package com.favourite.collections.infrastructure.code.repository;

import com.favourite.collections.infrastructure.code.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
    boolean existsByName(String name);
}
