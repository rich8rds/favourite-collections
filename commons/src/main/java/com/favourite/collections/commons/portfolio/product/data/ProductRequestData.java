/* Collections #2024 */
package com.favourite.collections.commons.portfolio.product.data;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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
public class ProductRequestData {
	@NotBlank(message = "Name required")
	private String name;

	@NotNull(message = "Unit price cannot be null")
	@Positive(message = "Value must be positive")
	private BigDecimal unitPrice;

	private String imageUrl;

	@Positive @NotNull(message = "Field required")
	@NotBlank(message = "Field required")
	private Integer availableQuantity;

	private Long colorId;

	@NotBlank(message = "Field is required")
	private String description;

	@NotBlank(message = "Product must have a subcategory")
	private Long subcategoryId;
}
