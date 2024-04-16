/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.portfolio.product.domain;

import java.math.BigDecimal;
import javax.persistence.*;

import org.hibernate.validator.constraints.Length;

import com.favourite.collections.infrastructure.core.domain.AbstractAuditableCustom;

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
@Entity
@Table(name = "m_product")
public class Product extends AbstractAuditableCustom {
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "unit_price")
	private BigDecimal unitPrice;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "available_quantity")
	private Integer availableQuantity;

	@Column(name = "color")
	private String color;

	@Length(max = 4000) @Column(name = "description")
	private String description;
	// @OneToOne
	// @JoinColumn(name = "category_id")
	// private CodeValue category;

}
