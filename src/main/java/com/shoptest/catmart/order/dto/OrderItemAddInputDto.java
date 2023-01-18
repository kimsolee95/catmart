package com.shoptest.catmart.order.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * front 상품 상세 - 단일 상품 주문용 DTO
 * */
@Getter
@Setter
public class OrderItemAddInputDto {

  /* 상품 Id */
  private Long productItemId;

  /* 장바구니_상품 개수 */
  private int quantity;

}
