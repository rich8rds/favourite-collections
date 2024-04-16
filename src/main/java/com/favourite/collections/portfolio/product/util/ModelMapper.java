/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.portfolio.product.util;

import com.favourite.collections.portfolio.product.data.ProductData;
import com.favourite.collections.portfolio.product.domain.Product;

public class ModelMapper {
	public ProductData fromProductToData(Product product) {
		return ProductData.builder().name(product.getName()).description(product.getDescription())
				.color(product.getColor()).imageUrl(product.getImageUrl())
				.availableQuantity(product.getAvailableQuantity()).unitPrice(product.getUnitPrice()).build();
	}

	public Product fromDataToProduct(ProductData product) {
		return Product.builder().name(product.getName()).description(product.getDescription()).color(product.getColor())
				.imageUrl(product.getImageUrl()).availableQuantity(product.getAvailableQuantity())
				.unitPrice(product.getUnitPrice()).build();
	}
}
