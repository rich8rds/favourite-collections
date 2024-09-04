/* Collections #2024 */
package com.favourite.collections.portfolio.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.favourite.collections.portfolio.cart.domain.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	Optional<CartItem> findByProductId(Long productId);
}
