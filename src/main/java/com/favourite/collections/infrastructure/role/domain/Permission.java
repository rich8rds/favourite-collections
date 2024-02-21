/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.infrastructure.role.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.favourite.collections.infrastructure.core.domain.AbstractAuditableCustom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_permission")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Permission extends AbstractAuditableCustom implements GrantedAuthority {
	@Column(name = "grouping")
	private String grouping;

	@Column(name = "action_name")
	private String actionName;

	@Column(name = "entity_name")
	private String entityName;

	@Column(name = "display_name", unique = true)
	private String displayName;

	@Column(name = "description")
	private String description;

	@Column(name = "is_disabled")
	private Boolean isDisabled;

	@Override
	public String getAuthority() {
		return this.displayName;
	}
}
