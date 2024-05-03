/* Collections #2024 */
package com.favourite.collections.infrastructure.core.domain;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

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
