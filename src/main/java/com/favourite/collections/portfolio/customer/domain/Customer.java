/* Collections #2024 */
package com.favourite.collections.portfolio.customer.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.favourite.collections.infrastructure.core.domain.AbstractAuditableCustom;
import com.favourite.collections.infrastructure.core.domain.AppUser;

import lombok.*;

@Entity
@Table(name = "m_customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Customer extends AbstractAuditableCustom {
	@OneToOne
	@JoinColumn(name = "app_user_id")
	private AppUser appUser;
}
