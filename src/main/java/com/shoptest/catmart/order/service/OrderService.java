package com.shoptest.catmart.order.service;

import com.shoptest.catmart.order.dto.OrderItemAddInputDto;
import com.shoptest.catmart.order.dto.OrdersHistoryDetailDto;
import com.shoptest.catmart.order.dto.OrdersHistoryDto;
import java.util.List;

public interface OrderService {

  /* 고객 - 주문하기(장바구니 - 장바구니 내 모든 상픔 주문) */
  void createOrder(String email);

  /* 고객 - 주문하기 (상품 상세 페이지 - 단일 상품 주문) */
  void createOrder(OrderItemAddInputDto orderItemAddInputDto, String email);

  /* 고객 - 주문하기 목록 조회 */
  List<OrdersHistoryDto> selectOrdersHistoryList(String email);

  /* 고객 - 주문하기 목록 내 주문 단건 상세 조회 */
  List<OrdersHistoryDetailDto> selectOrdersHistoryDetailList(String email, Long ordersId);

  /* 고객 - 주문하기 목록 내 주문 취소하기(상태 변경) */
  void cancelOrder(String email, Long ordersId);

}
