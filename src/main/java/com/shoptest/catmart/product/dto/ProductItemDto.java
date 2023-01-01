package com.shoptest.catmart.product.dto;

import com.shoptest.catmart.product.type.ItemStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 상품 dto
 * */

@Getter
@Setter
@ToString
public class ProductItemDto {

  /* 상품 카테고리 ID */
  private Long categoryId;

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


}
