package com.shoptest.catmart.product.dto;

import com.shoptest.catmart.product.type.ItemStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 관리자 상품 목록 조회용 dto
 * */
@Getter
@Setter
@ToString
public class AdminProductMngDto {


  /* 상품 번호 id */
  private Long productItemId;

  /* 상품 이름 */
  private String itemName;

  /* 상품 상태 */
  private ItemStatus itemStatus;

  /* 상품가격 */
  private int price;

  /* 상품이 속한 카테고리명 */
  private String categoryName;

  /* 상품이 속한 카테고리의 id */
  private Long categoryId;

  /* 상품 게시 여부 */
  private boolean postYn;
}
