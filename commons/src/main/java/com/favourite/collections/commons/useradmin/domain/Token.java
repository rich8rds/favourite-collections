/* Collections #2024 */
package com.favourite.collections.commons.useradmin.domain;

import com.favourite.collections.commons.core.domain.AbstractPersistableCustom;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


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
public class Token extends AbstractPersistableCustom {
	@Column(name = "token", length = 500)
	private String token;

	@Column(name = "start_time")
	private Long startTime;

	@Column(name = "expiration_time")
	private Long expirationTime;

	@Column(name = "appuser_id", unique = true)
	private Long appUserId;
}
