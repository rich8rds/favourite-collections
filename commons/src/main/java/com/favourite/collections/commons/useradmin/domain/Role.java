/* Collections #2024 */
package com.favourite.collections.commons.useradmin.domain;

import java.util.HashSet;
import java.util.Set;

import com.favourite.collections.commons.core.domain.AbstractAuditableCustom;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role extends AbstractAuditableCustom  {
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
