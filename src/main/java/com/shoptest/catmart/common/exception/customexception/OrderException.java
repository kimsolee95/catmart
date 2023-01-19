package com.shoptest.catmart.common.exception.customexception;

import com.shoptest.catmart.common.exception.type.OrderErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * 주문 업무 - custom exception
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderException extends RuntimeException {

  private OrderErrorCode orderErrorCode;
  private String errorMessage;

  public OrderException(OrderErrorCode orderErrorCode) {
    this.orderErrorCode = orderErrorCode;
    this.errorMessage = orderErrorCode.getDescription();
  }

}
