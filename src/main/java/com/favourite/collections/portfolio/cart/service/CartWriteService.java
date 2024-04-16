package com.favourite.collections.portfolio.cart.service;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import org.springframework.http.ResponseEntity;

public interface CartWriteService {
    ResponseEntity<CommandResult> addProductToCart(Long productId);

    ResponseEntity<CommandResult> removeProductFromCart(Long itemId);

    ResponseEntity<CommandResult> clearCart(Long cartId);

    ResponseEntity<CommandResult> increaseQuantity(Long productId);

    ResponseEntity<CommandResult> decreaseQuantity(Long productId);
}