/* Collections #2024 */
package com.favourite.collections.commons.useradmin.domain;

import com.favourite.collections.commons.core.domain.AbstractAuditableCustom;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "m_cart_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CartItem extends AbstractAuditableCustom {
	@Column(name = "product_name")
	private String productName;

	@Column(name = "unit_price")
	private BigDecimal unitPrice;

	@Column(name = "order_quantity")
	private Integer orderQuantity;

	@Column(name = "sub_total")
	private BigDecimal subTotal;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@Column(name = "image_url")
	private String imageUrl;
}
