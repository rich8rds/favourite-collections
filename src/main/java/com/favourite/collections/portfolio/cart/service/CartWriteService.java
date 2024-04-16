/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.portfolio.cart.service;

import org.springframework.http.ResponseEntity;

import com.favourite.collections.infrastructure.core.data.CommandResult;

public interface CartWriteService {
	ResponseEntity<CommandResult> addProductToCart(Long productId);

	ResponseEntity<CommandResult> removeProductFromCart(Long itemId);

	ResponseEntity<CommandResult> clearCart(Long cartId);

	ResponseEntity<CommandResult> increaseQuantity(Long productId);

	ResponseEntity<CommandResult> decreaseQuantity(Long productId);
}
