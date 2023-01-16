package com.shoptest.catmart.common.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderErrorCode {

  USER_EMAIL_NOT_EXIST("해당 이메일을 가진 사용자가 없습니다."),
  OVER_QUANTITY("주문하신 상품의 수량이 현재 상품 재고량을 초과합니다."),
  OUT_OF_STOCK("주문하신 상품은 현재 품절 상태입니다."),
  NOT_EXIST_PRODUCT_ITEM("주문하신 상품은 존재하지 않는 상품입니다."),
  NOT_EXIST_CART_ITEM("주문할 수 있는 장바구니 상품을 담아주세요.");

  private final String description;
}
