/* Richards-Favour #2024 */
package com.favourite.collections.portfolio.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.favourite.collections.portfolio.cart.domain.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
