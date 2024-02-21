/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.portfolio.product.service.impl;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.core.data.SearchParameters;
import com.favourite.collections.portfolio.product.data.ProductData;
import com.favourite.collections.portfolio.product.repository.ProductRepository;
import com.favourite.collections.portfolio.product.service.ProductReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductReadServiceImpl implements ProductReadService {
	private final ProductRepository productRepository;

	@Override
	public ResponseEntity<Page<ProductData>> retrieveAllProducts(SearchParameters searchParameters) {
		return ResponseEntity.ok().body(productRepository.findBy(searchParameters));
	}

	@Override
	public ResponseEntity<CommandResult> retrieveProduct(Long productId) {
		return null;
	}
}
