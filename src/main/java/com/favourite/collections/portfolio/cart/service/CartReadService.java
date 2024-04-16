/* Richards-Favour #2024 */
package com.favourite.collections.portfolio.cart.service;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.favourite.collections.portfolio.cart.domain.CartItem;

public interface CartReadService {
	ResponseEntity<Set<CartItem>> viewCartItems();
}
