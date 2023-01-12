package com.shoptest.catmart.common.exception.model;

import com.shoptest.catmart.common.exception.type.CartErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *  장바구니 API 에러 공통 응답 객체
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartErrorResponse {

  private CartErrorCode errorCode;
  private String errorMessage;

}
