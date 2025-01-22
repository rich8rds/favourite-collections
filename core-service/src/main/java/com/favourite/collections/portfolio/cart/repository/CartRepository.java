/* Collections #2024 */
package com.favourite.collections.portfolio.cart.repository;

import com.favourite.collections.commons.useradmin.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
