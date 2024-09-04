/* Collections #2024 */
package com.favourite.collections.portfolio.cart.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.portfolio.cart.domain.CartItem;
import com.favourite.collections.portfolio.cart.service.CartReadService;
import com.favourite.collections.portfolio.cart.service.CartWriteService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Cart")
@Slf4j
@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {
	private final CartWriteService cartWriteService;
	private final CartReadService cartReadService;

	@PostMapping("/item/{productId}")
	public ResponseEntity<CommandResult> addProductToCart(@PathVariable Long productId) {
		return cartWriteService.addProductToCart(productId);
	}

	@DeleteMapping("/item/{itemId}")
	public ResponseEntity<CommandResult> removeProductFromCart(@PathVariable Long itemId) {
		return cartWriteService.removeProductFromCart(itemId);
	}

	@PutMapping("/item/{productId}")
	public ResponseEntity<CommandResult> increaseOrDecreaseQuantity(@PathVariable Long productId,
			@RequestParam(name = "command", defaultValue = "none") String command) {

		if (command.equals("increase")) {
			return cartWriteService.increaseQuantity(productId);
		} else if (command.equals("decrease")) {
			return cartWriteService.decreaseQuantity(productId);
		} else if (command.equals("none")) {
			return ResponseEntity.ok(CommandResult.builder().build());
		} else {
			return ResponseEntity.status(400)
					.body(CommandResult.builder()
							.message("Wrong command used to add or decrease quantity for cart item")
							.response("Supported commands are 'increase' and 'decrease'").build());
		}
	}

	@DeleteMapping("/{cartId}")
	public ResponseEntity<CommandResult> clearCart(@PathVariable Long cartId) {
		return cartWriteService.clearCart(cartId);
	}

	@GetMapping
	public ResponseEntity<Set<CartItem>> viewCartItems() {
		return cartReadService.viewCartItems();
	}
}
