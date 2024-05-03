/* Collections #2024 */
package com.favourite.collections.portfolio.product.service.impl;

import com.favourite.collections.infrastructure.core.domain.AppUser;
import com.favourite.collections.infrastructure.security.util.AppContextUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.favourite.collections.infrastructure.code.domain.CodeValue;
import com.favourite.collections.infrastructure.code.repository.CodeValueRepository;
import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.core.exceptions.AbstractPlatformException;
import com.favourite.collections.portfolio.product.data.ProductRequestData;
import com.favourite.collections.portfolio.product.domain.Product;
import com.favourite.collections.portfolio.product.repository.ProductRepository;
import com.favourite.collections.portfolio.product.service.ProductWriteService;
import com.favourite.collections.portfolio.product.util.ModelMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductWriteServiceImpl implements ProductWriteService {
	private final ProductRepository productRepository;
	private final ModelMapper mapper = new ModelMapper();
	private final CodeValueRepository codeValueRepository;
	private final AppContextUser appContextUser;

	@Override
	public ResponseEntity<CommandResult> addNewProduct(ProductRequestData productRequestData) {
		AppUser loggedInUser = appContextUser.authenticated();

		String productName = productRequestData.getName();
		Long colorId = productRequestData.getColorId();
		Long subcategoryId = productRequestData.getSubcategoryId();

		CodeValue colorCodeValue = this.codeValueRepository.findById(colorId)
				.orElseThrow(() -> new AbstractPlatformException("error.subcategory.not.found",
						"Color code value with identifier " + colorId + " not found", 404));

		CodeValue subcategoryCodeValue = this.codeValueRepository.findById(subcategoryId)
				.orElseThrow(() -> new AbstractPlatformException("error.subcategory.not.found",
						" Subcategory code value with identifier " + subcategoryId + " not found", 404));

		if (this.productRepository.existsByName(productName)) {
			throw new AbstractPlatformException("error.product.already.exists.by.name", "Product name exists", 409);
		}

		Product product = mapper.fromDataToProduct(productRequestData);
		product.setSubcategory(subcategoryCodeValue);
		product.setColor(colorCodeValue);
		return ResponseEntity.ok(CommandResult.builder().entityId(product.getId()).message("Product added successfully")
				.response("Product added!").build());
	}

	@Override
	public ResponseEntity<CommandResult> updateProduct(Long productId, ProductRequestData productRequestData) {
		return null;
	}
}
