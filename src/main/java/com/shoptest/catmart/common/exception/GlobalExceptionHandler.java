package com.shoptest.catmart.common.exception;

import com.shoptest.catmart.common.exception.customexception.CartException;
import com.shoptest.catmart.common.exception.customexception.MemberException;
import com.shoptest.catmart.common.exception.customexception.OrderException;
import com.shoptest.catmart.common.exception.model.CartErrorResponse;
import com.shoptest.catmart.common.exception.model.MemberErrorResponse;
import com.shoptest.catmart.common.exception.model.OrderErrorResponse;
import com.shoptest.catmart.common.exception.model.ParamValidErrorResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
  @ResponseBody
  @ResponseStatus(HttpStatus.PRECONDITION_FAILED) // 412 code (요청의 사전조건 만족 x)
  @ExceptionHandler(MemberException.class)
  public MemberErrorResponse handleMemberException(MemberException e, Model model) {

    log.error("{} is occurred.", e.getMemberErrorCode());
    return new MemberErrorResponse(e.getMemberErrorCode(), e.getErrorMessage());
  }

  /**
   * DTO @Valid 유효성 검사 실패 시, exception
   * */
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ParamValidErrorResponse methodValidException(MethodArgumentNotValidException e, HttpServletRequest request, Model model) {

    log.error("methodValidException is occurred. url: {}, trace: {}", request.getRequestURI(), e.getStackTrace());

    BindingResult bindingResult = e.getBindingResult();
    String errorMessage = bindingResult.hasErrors() ? bindingResult.getFieldError().getDefaultMessage() : "";
    String code = "Parameter Validate Fail";

    return new ParamValidErrorResponse(code, errorMessage);
  }

}
