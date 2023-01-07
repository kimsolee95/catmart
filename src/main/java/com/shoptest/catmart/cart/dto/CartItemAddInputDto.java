package com.shoptest.catmart.cart.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * front 상품 상세 - 장바구니 상품 추가용 DTO
 * */
@Getter
@Setter
public class CartItemAddInputDto {

  /* 상품 Id */
  private Long productItemId;

  /* 장바구니_상품 개수 */
  private int quantity;

}
