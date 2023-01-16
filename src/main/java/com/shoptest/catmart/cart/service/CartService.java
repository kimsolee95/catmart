package com.shoptest.catmart.cart.service;

import com.shoptest.catmart.cart.domain.CartItem;
import com.shoptest.catmart.cart.dto.CartItemAddInputDto;
import com.shoptest.catmart.cart.dto.CartItemDeleteInputDto;
import com.shoptest.catmart.cart.dto.CartItemDetailDto;
import com.shoptest.catmart.cart.dto.CartItemUpdateInputDto;
import java.util.List;

public interface CartService {

  /* 고객 - 장바구니 상품 담기 */
  Long addItemInCart(String email, CartItemAddInputDto parameter);

  /* 고객 - 장바구니 내역 목록 조회 */
  List<CartItemDetailDto> selectCartItemDetailList(String email);

  /* 고객 - 장바구니 내역 목록 > 장바구니 상품 수량 변경 */
  Long updateItemQuantityInCart(String email, CartItemUpdateInputDto parameter);

  /* 고객 - 장바구니 내역 목록 > 장바구니 상품 삭제 */
  Long deleteItemInCart(String email, Long cartItemId);

  /* 주문 처리 이후, 고객의 장바구니_상품 일괄 삭제 */
  void deleteAllCartItem(Long cartId);

}
