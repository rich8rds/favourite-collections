/* Collections #2024 */
package com.favourite.collections.commons.useradmin.domain;

import com.favourite.collections.commons.core.domain.AbstractAuditableCustom;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//import org.springframework.security.core.GrantedAuthority;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "m_permission")
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
