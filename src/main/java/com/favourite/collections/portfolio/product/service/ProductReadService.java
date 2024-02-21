/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.portfolio.product.service;

import com.favourite.collections.portfolio.product.data.ProductData;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.core.data.SearchParameters;

public interface ProductReadService {
	ResponseEntity<Page<ProductData>> retrieveAllProducts(SearchParameters searchParameters);

	ResponseEntity<CommandResult> retrieveProduct(Long productId);
}
