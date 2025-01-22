/* Collections #2024 */
package com.favourite.collections.portfolio.cart.service;

import java.util.Set;

import com.favourite.collections.commons.useradmin.domain.CartItem;
import org.springframework.http.ResponseEntity;

public interface CartReadService {
	ResponseEntity<Set<CartItem>> viewCartItems();
}
