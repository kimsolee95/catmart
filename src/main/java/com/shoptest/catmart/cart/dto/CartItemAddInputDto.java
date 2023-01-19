package com.shoptest.catmart.cart.dto;

import javax.validation.constraints.Min;
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
  @Min(value = 1, message = "장바구니에 담을 상품의 개수는 1개 이상이어야 합니다.")
  private int quantity;

}
