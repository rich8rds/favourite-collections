/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.portfolio.product.repository;

import com.favourite.collections.infrastructure.core.data.SearchParameters;
import com.favourite.collections.portfolio.product.data.ProductData;
import org.springframework.data.domain.Page;

public interface ProductRepositoryCustom {
	Page<ProductData> findBy(SearchParameters searchParameters);
}
