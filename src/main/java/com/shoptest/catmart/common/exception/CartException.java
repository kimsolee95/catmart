package com.shoptest.catmart.common.exception;

import com.shoptest.catmart.common.exception.type.CartErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* 장바구니 업무 - custom exception
* */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartException extends RuntimeException {

  private CartErrorCode cartErrorCode;
  private String errorMessage;

  public CartException(CartErrorCode cartErrorCode) {
    this.cartErrorCode = cartErrorCode;
    this.errorMessage = cartErrorCode.getDescription();
  }

}
