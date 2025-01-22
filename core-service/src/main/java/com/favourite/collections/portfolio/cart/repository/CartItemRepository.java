/* Collections #2024 */
package com.favourite.collections.portfolio.cart.repository;

import java.util.Optional;

import com.favourite.collections.commons.useradmin.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	Optional<CartItem> findByProductId(Long productId);
}
