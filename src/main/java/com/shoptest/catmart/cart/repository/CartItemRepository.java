package com.shoptest.catmart.cart.repository;

import com.shoptest.catmart.cart.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {



}
