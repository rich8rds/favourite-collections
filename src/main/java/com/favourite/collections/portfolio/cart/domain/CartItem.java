/* Richards-Favour #2024 */
package com.favourite.collections.portfolio.cart.domain;

import java.math.BigDecimal;
import javax.persistence.*;

import com.favourite.collections.infrastructure.core.domain.AbstractAuditableCustom;
import com.favourite.collections.portfolio.product.domain.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
