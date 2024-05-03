/* Collections #2024 */
package com.favourite.collections.portfolio.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.favourite.collections.portfolio.product.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
	boolean existsByName(String name);
}
