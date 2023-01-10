package com.shoptest.catmart.cart.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * front 장바구니 내역 - 장바구니 상품수량 delete DTO
 * */
@Getter
@Setter
public class CartItemDeleteInputDto {

  /* 상품 Id */
  private Long cartItemId;

}
