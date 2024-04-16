/* Richards-Favour #2024 */
package com.favourite.collections.portfolio.product.service;

import org.springframework.http.ResponseEntity;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.portfolio.product.data.ProductCreateData;

public interface ProductWriteService {
	ResponseEntity<CommandResult> addNewProduct(ProductCreateData productFetchData);
}
