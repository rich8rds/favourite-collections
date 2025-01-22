/* Collections #2024 */
package com.favourite.collections.portfolio.customer.domain;

import java.util.HashSet;
import java.util.Set;

import com.favourite.collections.commons.core.domain.AbstractAuditableCustom;
import com.favourite.collections.commons.useradmin.domain.Address;
import com.favourite.collections.commons.useradmin.domain.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
	@ToString.Exclude
	Set<Address> addresses = new HashSet<>();
}
