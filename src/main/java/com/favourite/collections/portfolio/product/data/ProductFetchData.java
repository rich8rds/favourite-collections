/* Richards-Favour #2024 */
package com.favourite.collections.portfolio.product.data;

import java.math.BigDecimal;

import com.favourite.collections.infrastructure.code.domain.CodeValue;

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
public class ProductFetchData {
	private String name;
	private BigDecimal unitPrice;
	private String imageUrl;
	private Integer availableQuantity;
	private CodeValue color;
	private String description;
	private CodeValue subcategoryId;
}
