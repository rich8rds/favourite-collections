/* Collections #2024 */
package com.favourite.collections.commons.useradmin.domain;

import com.favourite.collections.commons.core.domain.AbstractAuditableCustom;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

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
