package com.shoptest.catmart.order.type;
/**
 * 주문 상태 종류 (주문 접수(결제 전) / 주문 취소/ 결제완료)
 * */
public enum OrderStatus {
  TAKE_ORDER, CANCEL_ORDER, PAY_FOR_ORDER
}
