/* Richards-Favour #2024 */
package com.favourite.collections.portfolio.cart.domain;

import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.favourite.collections.infrastructure.core.domain.AbstractPersistableCustom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "m_cart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Cart extends AbstractPersistableCustom {
	@OneToMany
	private Set<CartItem> items;

	@Column(name = "total")
	private BigDecimal total = BigDecimal.ZERO;
}
