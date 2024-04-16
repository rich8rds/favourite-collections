/* Richards-Favour #2024 */
package com.favourite.collections.portfolio.product.api;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.favourite.collections.infrastructure.core.data.CommandResult;
import com.favourite.collections.infrastructure.core.data.SearchParameters;
import com.favourite.collections.portfolio.product.data.ProductCreateData;
import com.favourite.collections.portfolio.product.data.ProductFetchData;
import com.favourite.collections.portfolio.product.service.ProductReadService;
import com.favourite.collections.portfolio.product.service.ProductWriteService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Products")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
	private final ProductWriteService productWriteService;
	private final ProductReadService productReadService;

	@GetMapping
	public ResponseEntity<Page<ProductFetchData>> retrieveAllProducts(@RequestParam(required = false) Long id,
			@RequestParam(required = false) String name, @RequestParam(defaultValue = "1") Integer offset,
			@RequestParam(defaultValue = "10") Integer limit, @RequestParam(defaultValue = "DESC") String sortOrder,
			@RequestParam(defaultValue = "id") String orderBy, @RequestParam(defaultValue = "all") String category,
			@RequestParam(defaultValue = "all") String subcategory,
			@RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
			@RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

		SearchParameters searchParameters = SearchParameters.builder().id(id).offset(offset).limit(limit).name(name)
				.category(category).subcategory(subcategory).sortOrder(sortOrder).orderBy(orderBy).startDate(startDate)
				.endDate(endDate).build();

		return productReadService.retrieveAllProducts(searchParameters);
	}

	@PostMapping
	public ResponseEntity<CommandResult> addNewProduct(@RequestBody ProductCreateData productCreateData) {
		return productWriteService.addNewProduct(productCreateData);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<CommandResult> retrieveProduct(@PathVariable Long productId) {
		return productReadService.retrieveProduct(productId);
	}
}
