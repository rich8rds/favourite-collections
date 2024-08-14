/* Collections #2024 */
package com.favourite.collections.infrastructure.useradmin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.favourite.collections.infrastructure.core.domain.AbstractAuditableCustom;
import com.favourite.collections.infrastructure.core.domain.AbstractPersistableCustom;

import lombok.*;

@Entity
@Table(name = "m_code_value", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"code_id", "code_value"}, name = "code_value_duplicate")})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CodeValue extends AbstractAuditableCustom {

	@Column(name = "code_value", length = 100)
	private String label;

	@Column(name = "order_position")
	private int position;

	@Column(name = "code_description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "code_id", nullable = false)
	private Code code;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "is_mandatory")
	private boolean mandatory;
}
