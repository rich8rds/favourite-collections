/* RICHARDS AND FAVOUR (C)2024 */
package com.favourite.collections.portfolio.product.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.portfolio.product.data.ProductData;
import com.favourite.collections.portfolio.product.repository.ProductRepository;
import com.favourite.collections.portfolio.product.service.ProductWriteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductWriteServiceImpl implements ProductWriteService {
	private final ProductRepository productRepository;

	@Override
	public ResponseEntity<CommandResult> addNewProduct(ProductData productData) {
		return null;
	}
}
