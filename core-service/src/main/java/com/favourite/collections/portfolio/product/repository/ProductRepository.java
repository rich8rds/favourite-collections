/* Collections #2024 */
package com.favourite.collections.portfolio.product.repository;

import com.favourite.collections.commons.useradmin.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
	boolean existsByName(String name);
}
