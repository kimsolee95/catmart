package com.shoptest.catmart.common.exception;

import com.shoptest.catmart.common.exception.model.CartErrorResponse;
import com.shoptest.catmart.common.exception.model.OrderErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 장바구니 API 호출 에러 핸들러
   * */
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(CartException.class)
  public CartErrorResponse handleCartException(CartException e) {

    log.error("{} is occurred.", e.getCartErrorCode());
    return new CartErrorResponse(e.getCartErrorCode(), e.getErrorMessage());
  }

  /**
   * 주문 API 호출 에러 핸들러
   * */
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(OrderException.class)
  public OrderErrorResponse handleOrderException(OrderException e) {

    log.error("{} is occurred.", e.getOrderErrorCode());
    return new OrderErrorResponse(e.getOrderErrorCode(), e.getErrorMessage());
  }

  /**
   * 회원 업무용 에러 핸들러 - error page 반환용
   * */
  @ResponseStatus(HttpStatus.PRECONDITION_FAILED) // 412 code (요청의 사전조건 만족 x)
  @ExceptionHandler(MemberException.class)
  public String handleMemberException(MemberException e, Model model) {

    log.error("{} is occurred.", e.getMemberErrorCode());

    model.addAttribute("errorMessage", e.getErrorMessage());
    return "error/member-error";
  }

}
