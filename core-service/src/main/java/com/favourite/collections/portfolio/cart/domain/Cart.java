/* Collections #2024 */
package com.favourite.collections.portfolio.cart.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
	private Set<CartItem> items = new HashSet<>();

	@Column(name = "total", scale = 2)
	private BigDecimal total = BigDecimal.ZERO;
}
