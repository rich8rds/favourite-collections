/* Collections #2024 */
package com.favourite.collections.infrastructure.useradmin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.favourite.collections.infrastructure.core.domain.AbstractPersistableCustom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "m_address")
public class Address extends AbstractPersistableCustom {

	@Column(name = "street_number")
	private String streetNumber;

	@Column(name = "junction")
	private String junction;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "is_default")
	private Boolean isDefault;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "appuser_id")
	private AppUser appUser;
}
