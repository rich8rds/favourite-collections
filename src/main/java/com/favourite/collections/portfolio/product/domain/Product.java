/* Richards-Favour #2024 */
package com.favourite.collections.portfolio.product.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.favourite.collections.infrastructure.code.domain.CodeValue;
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

	@OneToOne
	@JoinColumn(name = "color_id", nullable = false)
	private CodeValue color;

	@Length(max = 4000) @Column(name = "description")
	private String description;

	@OneToOne
	@JoinColumn(name = "subcategory_id", nullable = false)
	private CodeValue subcategory;
}
