/* Collections #2024 */
package com.favourite.collections.portfolio.product.service;

import com.favourite.collections.commons.core.data.CommandResult;
import com.favourite.collections.commons.core.data.SearchParameters;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.favourite.collections.commons.portfolio.product.data.ProductFetchData;

public interface ProductReadService {
	ResponseEntity<Page<ProductFetchData>> retrieveAllProducts(SearchParameters searchParameters);

	ResponseEntity<CommandResult> retrieveProduct(Long productId);
}
