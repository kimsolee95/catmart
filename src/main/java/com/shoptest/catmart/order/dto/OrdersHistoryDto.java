package com.shoptest.catmart.order.dto;

import com.shoptest.catmart.order.type.OrderStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * front 주문 내역 - 주문 내역 목록 고객 조회용 DTO
 * */
@Getter
@Setter
public class OrdersHistoryDto {

  /* 주문 id */
  private Long ordersId;

  /* 주문 상품 종류 개수 */
  private int kindsCount;

  /* 주문 일자 */
  private LocalDateTime ordersDate;

  /* 주문 상태 */
  private OrderStatus ordersStatus;

  /* 회원 id */
  private Long memberId;

  /* 총 상품 주문 금액 */
  private Long totalOrderPrice;

  /* 총 상품 주문 금액에 배송비 더해진 금액 */
  private Long shippingFeeAddedPrice;

  /* 상품 id -> 해당 주문상품 중 상품 id가 가장 적은 것 */
  private Long productItemId;

  /* 해당 주문의 주문상품 id 모두 문자열로 더한 것 */
  private String ordersItemIdList;

  /* 주문상품의 총 수량 개수 */
  private int sumQuantity;

  /* 주문 내역 출력용 상품이름 정보 */
  private String itemNameInfo;

  /* 주문 내역 출력용 상품이름에 대한 출력용 url img path */
  private String urlImgPath;

  public String getOrdersDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
    return ordersDate != null ? ordersDate.format(formatter) : "";
  }

}
