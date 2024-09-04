/* Collections #2024 */
package com.favourite.collections.infrastructure.useradmin.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import com.favourite.collections.infrastructure.core.domain.AbstractAuditableCustom;

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
