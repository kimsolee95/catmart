package com.shoptest.catmart.order.dto;

import javax.validation.constraints.Min;
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

  /* 주문 희망 상품 개수 */
  @Min(value = 1, message = "주문할 상품의 개수는 1개 이상이어야 합니다.")
  private int quantity;

}
