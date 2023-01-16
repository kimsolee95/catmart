package com.shoptest.catmart.common.exception.model;

import com.shoptest.catmart.common.exception.type.OrderErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  주문 API 에러 공통 응답 객체
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderErrorResponse {

  private OrderErrorCode errorCode;
  private String errorMessage;
}
