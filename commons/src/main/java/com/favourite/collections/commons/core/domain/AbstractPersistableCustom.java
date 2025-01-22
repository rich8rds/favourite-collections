/* Collections #2024 */
package com.favourite.collections.commons.core.domain;

import java.io.Serializable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonInclude;

@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractPersistableCustom implements Persistable<Long>, Serializable {

	private static final long serialVersionUID = 9181640245194492646L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	protected void setId(final Long id) {
		this.id = id;
	}

	@Override
	@Transient
	public boolean isNew() {
		return null == this.id;
	}
}
