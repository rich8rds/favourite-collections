/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.code.domain;

import com.favourite.collections.infrastructure.core.domain.AbstractPersistableCustom;
import lombok.*;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
