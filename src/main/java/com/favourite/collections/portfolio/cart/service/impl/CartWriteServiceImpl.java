/* Collections #2024 */
package com.favourite.collections.portfolio.cart.service.impl;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.core.data.CommandResultBuilder;
import com.favourite.collections.infrastructure.core.domain.AppUser;
import com.favourite.collections.infrastructure.core.exceptions.AbstractPlatformException;
import com.favourite.collections.infrastructure.security.repository.AppUserRepository;
import com.favourite.collections.infrastructure.security.util.AppContextUser;
import com.favourite.collections.portfolio.cart.domain.Cart;
import com.favourite.collections.portfolio.cart.domain.CartItem;
import com.favourite.collections.portfolio.cart.repository.CartItemRepository;
import com.favourite.collections.portfolio.cart.repository.CartRepository;
import com.favourite.collections.portfolio.cart.service.CartWriteService;
import com.favourite.collections.portfolio.product.domain.Product;
import com.favourite.collections.portfolio.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartWriteServiceImpl implements CartWriteService {
	private final AppUserRepository appUserRepository;
	private final AppContextUser appContextUser;
	private final ProductRepository productRepository;
	private final CartItemRepository cartItemRepository;
	private final CartRepository cartRepository;

	@Override
	public ResponseEntity<CommandResult> addProductToCart(Long productId) {
		AppUser loggedInUser = appContextUser.authenticated();
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new AbstractPlatformException("error.product.id.not.found", "Product not available", 404));

		Cart cart = loggedInUser.getCart();
		Set<CartItem> allCartItems = cart.getItems();

		if (product.getAvailableQuantity() == 0) {
			throw new AbstractPlatformException("error.product.out.of.stock", "Product is out of stock", 404);
		}

		CartItem cartItem = cartItemRepository.findByProductId(productId)
				.orElseThrow(() -> new AbstractPlatformException("Product with id " + productId + "not found",
						"Product does not exist", 404));

		if (cartItem != null) {
			increaseQuantity(productId);
			return ResponseEntity.ok(new CommandResultBuilder().response("Item updated").message("Quantity updated")
					.entityId(cart.getId()).build());
		}

		CartItem newCartItem = CartItem.builder().imageUrl(product.getImageUrl()).productName(product.getName())
				.unitPrice(product.getUnitPrice()).orderQuantity(1).product(product).cart(cart).build();
		newCartItem.setSubTotal(BigDecimal.valueOf(newCartItem.getOrderQuantity()).multiply(product.getUnitPrice()));

		cartItemRepository.save(newCartItem);
		allCartItems.add(newCartItem);

		cart.setItems(allCartItems);
		BigDecimal cartTotal = BigDecimal.ZERO;

		for (CartItem item : cart.getItems()) {
			cartTotal = cartTotal.add(item.getSubTotal());
		}

		cart.setTotal(cartTotal);
		cartRepository.save(cart);
		appUserRepository.saveAndFlush(loggedInUser);
		return ResponseEntity
				.ok(new CommandResultBuilder().response("Item added to cart").entityId(cart.getId()).build());
	}

	@Override
	public ResponseEntity<CommandResult> removeProductFromCart(Long itemId) {
		AppUser loggedInUser = appContextUser.authenticated();
		CartItem cartItem = cartItemRepository.findById(itemId)
				.orElseThrow(() -> new AbstractPlatformException("Item is not in cart", "Item does not exist", 404));

		Cart cart = loggedInUser.getCart();
		Set<CartItem> cartItems = cart.getItems();
		if (cartItems.contains(cartItem)) {
			cartItemRepository.delete(cartItem);
		}
		return ResponseEntity.ok(new CommandResultBuilder().response("Item deleted from cart").entityId(cart.getId())
				.resourceId(String.valueOf(cartItem.getId())).build());
	}

	@Override
	public ResponseEntity<CommandResult> clearCart(Long cartId) {
		AppUser loggedInUser = this.appContextUser.authenticated();
		Cart cart = loggedInUser.getCart();
		Set<CartItem> cartItems = cart.getItems();
		cartItems.clear();
		this.cartRepository.save(cart);
		return ResponseEntity.ok(new CommandResultBuilder().response("Item deleted from cart").entityId(cart.getId())
				.resourceId(String.valueOf(cart.getId())).build());
	}

	@Override
	public ResponseEntity<CommandResult> increaseQuantity(Long productId) {
		AppUser loggedInUser = this.appContextUser.authenticated();
		Cart cart = loggedInUser.getCart();

		CartItem savedCartItem = this.cartItemRepository.findByProductId(productId)
				.orElseThrow(() -> new AbstractPlatformException("Product with id " + productId + "not found",
						"Product does not exist", 404));

		Product product = this.productRepository.findById(productId).orElseThrow(
				() -> new AbstractPlatformException("Product with id " + productId + " not found", "Not found", 404));

		if (product.getAvailableQuantity() == 0) {
			throw new AbstractPlatformException("error.product.out.of.stock", "Product is out of stock", 404);
		}

		Set<CartItem> cartItems = cart.getItems();

		for (CartItem item : cartItems) {
			if (Objects.equals(item.getProduct().getId(), savedCartItem.getProduct().getId())) {
				savedCartItem.setOrderQuantity(item.getOrderQuantity() + 1);
				savedCartItem
						.setSubTotal(item.getUnitPrice().multiply(new BigDecimal(savedCartItem.getOrderQuantity())));
				cartItemRepository.save(savedCartItem);
			}
		}

		BigDecimal cartTotal = BigDecimal.ZERO;
		cartTotal = cartTotal.add(savedCartItem.getUnitPrice());
		cart.setTotal(cartTotal);
		this.cartRepository.save(cart);

		return ResponseEntity.ok(CommandResult.builder().message("Item increased in cart").build());
	}

	@Override
	public ResponseEntity<CommandResult> decreaseQuantity(Long productId) {
		AppUser loggedInUser = this.appContextUser.authenticated();

		Cart cart = loggedInUser.getCart();

		CartItem savedCartItem = this.cartItemRepository.findByProductId(productId)
				.orElseThrow(() -> new AbstractPlatformException("Product with id " + productId + "not found",
						"Product does not exist", 404));

		Product product = this.productRepository.findById(productId).orElseThrow(
				() -> new AbstractPlatformException("Product with id " + productId + " not found", "Not found", 404));

		if (product.getAvailableQuantity() == 0) {
			throw new AbstractPlatformException("error.product.out.of.stock", "Product is out of stock", 404);
		}

		Set<CartItem> cartItems = cart.getItems();

		for (CartItem item : cartItems) {
			if (Objects.equals(item.getProduct().getId(), savedCartItem.getProduct().getId())) {
				savedCartItem.setOrderQuantity(item.getOrderQuantity() - 1);
				savedCartItem
						.setSubTotal(item.getUnitPrice().multiply(new BigDecimal(savedCartItem.getOrderQuantity())));
				cartItemRepository.save(savedCartItem);
			}
		}

		BigDecimal cartTotal = BigDecimal.ZERO;
		cartTotal = cartTotal.subtract(savedCartItem.getUnitPrice());
		cart.setTotal(cartTotal);
		this.cartRepository.save(cart);

		return ResponseEntity.ok(CommandResult.builder().message("Item decreased in cart").build());
	}
}
