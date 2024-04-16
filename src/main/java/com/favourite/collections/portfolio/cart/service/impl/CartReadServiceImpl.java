/* Richards-Favour #2024 */
package com.favourite.collections.portfolio.cart.service.impl;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.favourite.collections.infrastructure.core.domain.AppUser;
import com.favourite.collections.infrastructure.core.exceptions.AbstractPlatformException;
import com.favourite.collections.infrastructure.security.util.AppContextUser;
import com.favourite.collections.portfolio.cart.domain.Cart;
import com.favourite.collections.portfolio.cart.domain.CartItem;
import com.favourite.collections.portfolio.cart.service.CartReadService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartReadServiceImpl implements CartReadService {
	private final AppContextUser appContextUser;

	@Override
	public ResponseEntity<Set<CartItem>> viewCartItems() {
		AppUser loggedInUser = this.appContextUser.authenticated();
		Cart cart = loggedInUser.getCart();
		Set<CartItem> cartItems = cart.getItems();
		if (cartItems.isEmpty()) {
			throw new AbstractPlatformException("error.cart.is.empty", "Cart is empty");
		}

		return ResponseEntity.status(200).body(cartItems);
	}
}
