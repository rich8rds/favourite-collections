/* Collections #2024 */
package com.favourite.collections.commons.useradmin.repository;

import java.util.Optional;

import com.favourite.collections.commons.useradmin.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
	Optional<Token> findByToken(String token);
}
