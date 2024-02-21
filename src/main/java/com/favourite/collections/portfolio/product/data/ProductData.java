/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.portfolio.product.data;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductData {
	private String name;
	private BigDecimal unitPrice;
	private String imageUrl;
	private Integer availableQuantity;
	private String color;
	private String description;
	private Long categoryId;
}
