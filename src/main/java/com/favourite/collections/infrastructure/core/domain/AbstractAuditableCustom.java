/* Collections #2024 */
package com.favourite.collections.infrastructure.core.domain;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.domain.Auditable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Slf4j
public abstract class AbstractAuditableCustom extends AbstractPersistableCustom
		implements
			Auditable<AppUser, Long, Instant> {
	private static final long serialVersionUID = 141481953116476081L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "createdby_id")
	private AppUser createdBy = authenticatedUserForAudit();

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lastmodifiedby_id")
	private AppUser lastModifiedBy;

	@Column(name = "lastmodified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate = new Date();

	@PreUpdate
	public void updateModification() {
		this.lastModifiedBy = authenticatedUserForAudit();
		this.lastModifiedDate = new Date();
	}

	@Override
	public Optional<AppUser> getCreatedBy() {
		return Optional.ofNullable(this.createdBy);
	}

	@Override
	public void setCreatedBy(final AppUser createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public Optional<Instant> getCreatedDate() {
		return null == this.createdDate ? Optional.empty() : Optional.of(this.createdDate.toInstant());
	}

	@Override
	public void setCreatedDate(final Instant createdDate) {
		this.createdDate = null == createdDate ? null : Date.from(createdDate);
	}

	@Override
	public Optional<AppUser> getLastModifiedBy() {
		return Optional.ofNullable(this.lastModifiedBy);
	}

	@Override
	public void setLastModifiedBy(final AppUser lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Override
	public Optional<Instant> getLastModifiedDate() {
		return null == this.lastModifiedDate ? Optional.empty() : Optional.of(this.lastModifiedDate.toInstant());
	}

	@Override
	public void setLastModifiedDate(final Instant lastModifiedDate) {
		this.lastModifiedDate = null == lastModifiedDate ? null : Date.from(lastModifiedDate);
	}

	public AppUser authenticatedUserForAudit() {

		AppUser currentUser = null;
		final SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			final Authentication auth = context.getAuthentication();
			if (auth != null) {
				try {
					currentUser = (AppUser) auth.getPrincipal();
				} catch (ClassCastException e) {
					log.error("class java.lang.String cannot be cast to class domain.AppUser");
					return null;
				}
			}
		}

		return currentUser;
	}
}
