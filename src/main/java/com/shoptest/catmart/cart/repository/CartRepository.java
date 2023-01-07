package com.shoptest.catmart.cart.repository;

import com.shoptest.catmart.cart.domain.Cart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

  Optional<Cart> findByMemberMemberId(Long memberId);

}
