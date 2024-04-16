/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.portfolio.product.repository;

import org.springframework.data.domain.Page;

import com.favourite.collections.infrastructure.core.data.SearchParameters;
import com.favourite.collections.portfolio.product.data.ProductData;

public interface ProductRepositoryCustom {
	Page<ProductData> findBy(SearchParameters searchParameters);
}
