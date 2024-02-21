/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.portfolio.customer.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.favourite.collections.infrastructure.core.domain.AbstractAuditableCustom;

@Entity
@Table(name = "t_customer")
public class Customer extends AbstractAuditableCustom {
}
