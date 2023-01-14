package com.shoptest.catmart.common.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CartErrorCode {

  USER_EMAIL_NOT_EXIST("해당 이메일을 가진 사용자가 없습니다."),
  USER_CART_NOT_EXIST("장바구니 관련 내부 서버 오류로 장바구니 상품의 상태 변경이 어렵습니다."),
  USER_CART_ITEM_NOT_EXIST("장바구니 관련 내부 서버 오류로 장바구니 상품의 상태 변경이 어렵습니다."),
  PRODUCT_ITEM_NOT_EXIST("선택하신 상품은 현재 장바구니에 담을 수 없는 상품입니다.");

  private final String description;
}
