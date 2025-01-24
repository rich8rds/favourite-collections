/* Collections #2024 */
package com.favourite.collections.portfolio.product.service;

import com.favourite.collections.commons.core.data.CommandResult;
import org.springframework.http.ResponseEntity;

import com.favourite.collections.commons.portfolio.product.data.ProductRequestData;

public interface ProductWriteService {
	ResponseEntity<CommandResult> addNewProduct(ProductRequestData productFetchData);

	ResponseEntity<CommandResult> updateProduct(Long productId, ProductRequestData productRequestData);
}
