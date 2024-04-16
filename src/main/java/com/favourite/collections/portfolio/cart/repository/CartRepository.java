package com.favourite.collections.portfolio.cart.repository;

import com.favourite.collections.portfolio.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
