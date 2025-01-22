/* Collections #2024 */
package com.favourite.collections.commons.useradmin.domain;

import java.util.Set;

import com.favourite.collections.commons.core.domain.AbstractPersistableCustom;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "m_code", uniqueConstraints = {@UniqueConstraint(columnNames = {"code_name"}, name = "code_name")})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Code extends AbstractPersistableCustom {

	@Column(name = "code_name", length = 100)
	private String name;

	@Column(name = "is_system_defined")
	private boolean systemDefined;

	@Column(name = "external_use")
	private Integer externalUse;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "code", orphanRemoval = true)
	private Set<CodeValue> values;
}
