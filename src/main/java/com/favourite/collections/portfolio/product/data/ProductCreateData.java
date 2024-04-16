/* Richards-Favour #2024 */
package com.favourite.collections.portfolio.product.data;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductCreateData {
	@NotNull(message = "Name required")
	@NotBlank(message = "Name required")
	private String name;
	@NotNull(message = "Unit price cannot be null")
	@Positive(message = "Value must be positive")
	private BigDecimal unitPrice;
	private String imageUrl;
	@Positive
	@NotNull(message = "Field required")
	@NotBlank(message = "Field required")
	private Integer availableQuantity;
	private Long colorId;
	@NotNull(message = "Description is required")
	@NotBlank(message = "Field is required")
	private String description;

	@NotNull(message = "Product must have a subcategory")
	@NotBlank(message = "Product must have a subcategory")
	private Long subcategoryId;
}
