/* Richards-Favour #2024 */
package com.favourite.collections.infrastructure.security.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.favourite.collections.infrastructure.core.domain.AbstractAuditableCustom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "m_token")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token extends AbstractAuditableCustom {
	@Column(name = "token", length = 500)
	private String token;

	@Column(name = "start_time")
	private Long startTime;

	@Column(name = "expiration_time")
	private Long expirationTime;

	@Column(name = "appuser_id", unique = true)
	// This references appuser id on t_appuser
	private Long appUserId;
}
