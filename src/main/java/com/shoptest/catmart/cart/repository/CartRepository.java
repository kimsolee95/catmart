package com.shoptest.catmart.cart.repository;

import com.shoptest.catmart.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
