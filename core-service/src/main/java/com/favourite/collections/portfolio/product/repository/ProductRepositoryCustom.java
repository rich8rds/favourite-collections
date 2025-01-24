/* Collections #2024 */
package com.favourite.collections.portfolio.product.repository;

import com.favourite.collections.commons.core.data.SearchParameters;
import org.springframework.data.domain.Page;

import com.favourite.collections.commons.portfolio.product.data.ProductFetchData;

public interface ProductRepositoryCustom {

	Page<ProductFetchData> findBy(SearchParameters searchParameters);
}
