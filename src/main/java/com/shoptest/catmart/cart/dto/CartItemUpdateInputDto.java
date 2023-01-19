package com.shoptest.catmart.cart.dto;

import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

/**
 * front 장바구니 내역 - 장바구니 상품수량 update DTO
 * */
@Getter
@Setter
public class  CartItemUpdateInputDto {

  /* 상품 Id */
  private Long cartItemId;

  /* 장바구니_상품 개수 */
  @Min(value = 1, message = "장바구니에 담을 상품의 개수는 1개 이상이어야 합니다.")
  private int quantity;

  /* 해당 상품 재고 */
  private int stock;

}
