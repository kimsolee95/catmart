package com.shoptest.catmart.order.service;

import com.shoptest.catmart.order.dto.OrdersHistoryDetailDto;
import com.shoptest.catmart.order.dto.OrdersHistoryDto;
import java.util.List;

public interface OrderService {

  /* 고객 - 주문하기(장바구니에서 접근) */
  void createOrder(String email);

  /* 고객 - 주문하기 목록 조회 */
  List<OrdersHistoryDto> selectOrdersHistoryList(String email);

  /* 고객 - 주문하기 목록 내 주문 단건 상세 조회 */
  List<OrdersHistoryDetailDto> selectOrdersHistoryDetailList(String email, Long ordersId);

}
