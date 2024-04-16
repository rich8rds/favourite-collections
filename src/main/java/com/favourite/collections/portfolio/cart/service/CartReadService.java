package com.favourite.collections.portfolio.cart.service;

import com.favourite.collections.portfolio.cart.domain.CartItem;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface CartReadService {
    ResponseEntity<Set<CartItem>> viewCartItems();
}
