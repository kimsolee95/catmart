package com.shoptest.catmart.order.dto;

import com.shoptest.catmart.order.type.OrderStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;

/**
 * front 주문 내역 - 주문 내역 상세 목록 고객 조회용 DTO
 * */
@Getter
@Setter
public class OrdersHistoryDetailDto {

  /* 주문 id */
  private Long ordersId;

  /* 주문 일자 */
  private LocalDateTime ordersDate;

  /* 주문 상태 */
  private OrderStatus ordersStatus;

  /* 회원 id */
  private Long memberId;

  /* 주문 상품 결제 금액 */
  private int orderPrice;

  /* 상품 id */
  private Long productItemId;

  /* 주문상품 id */
  private Long ordersItemId;

  /* 주문상품 수량 */
  private int quantity;

  /* 상품명 */
  private String itemName;

  /* 출력용 url img path */
  private String urlImgPath;

  public String getOrdersDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
    return ordersDate != null ? ordersDate.format(formatter) : "";
  }

}
