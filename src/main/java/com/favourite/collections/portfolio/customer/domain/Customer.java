/* Collections #2024 */
package com.favourite.collections.portfolio.customer.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.favourite.collections.infrastructure.core.domain.AbstractAuditableCustom;
import com.favourite.collections.infrastructure.useradmin.domain.Address;
import com.favourite.collections.infrastructure.useradmin.domain.AppUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

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

	@OneToMany
	Set<Address> addresses = new HashSet<>();
}
