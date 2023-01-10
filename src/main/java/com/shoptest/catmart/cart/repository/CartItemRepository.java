package com.shoptest.catmart.cart.repository;

import com.shoptest.catmart.cart.domain.CartItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

  Optional<CartItem> findByCartCartIdAndProductItemProductItemId(Long cartId, Long productItemId);

  Long deleteByCartCartIdAndCartItemId(Long cartId, Long cartItemId);

}
