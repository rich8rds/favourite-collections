/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.role.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.favourite.collections.infrastructure.core.domain.AbstractAuditableCustom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "m_role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Role extends AbstractAuditableCustom {
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "is_disabled")
	private Boolean isDisabled;

	@Column(name = "permission_id")
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Permission> permissions = new HashSet<>();
}
