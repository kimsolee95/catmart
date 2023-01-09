package com.shoptest.catmart.cart.service;

import com.shoptest.catmart.cart.dto.CartItemAddInputDto;
import com.shoptest.catmart.cart.dto.CartItemDetailDto;
import java.util.List;

public interface CartService {

  /* 고객 - 장바구니 상품 담기 */
  Long addItemInCart(String email, CartItemAddInputDto parameter);

  /* 고객 - 장바구니 내역 목록 조회 */
  List<CartItemDetailDto> selectCartItemDetailList(String email);

}
