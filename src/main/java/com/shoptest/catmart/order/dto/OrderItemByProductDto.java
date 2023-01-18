package com.shoptest.catmart.order.dto;

import com.shoptest.catmart.product.type.ItemStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * front 주문서 상품 정보 조회 - 상품 상세보기를 통한 바로 주문하기 시 주문서 내 주문상품 조회용 DTO
 * */
@Getter
@Setter
public class OrderItemByProductDto {

  /* 구매희망 상품개수 */
  private int quantity;

  /* 상품 ID */
  private Long productItemId;

  /* 상품이름 */
  private String itemName;

  /* 상품가격 */
  private int price;

  /* 상품 상태 - (판매중, 품절) */
  private ItemStatus itemStatus;

  /* 상품내용 */
  private String itemSubject;

  /* 상품재고 */
  private int stock;

  /* 상품 게시 여부 */
  private boolean postYn;

  private String urlImgPath;


}
