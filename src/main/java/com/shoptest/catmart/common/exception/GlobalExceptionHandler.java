package com.shoptest.catmart.common.exception;

import com.shoptest.catmart.common.exception.model.CartErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  /*
  * 장바구니 API 호출 에러 핸들러
  * */
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(CartException.class)
  public CartErrorResponse handleCartException(CartException e) {

    log.error("{} is ocurred.", e.getCartErrorCode());
    return new CartErrorResponse(e.getCartErrorCode(), e.getErrorMessage());
  }

}