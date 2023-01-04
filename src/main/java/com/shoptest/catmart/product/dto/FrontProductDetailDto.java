package com.shoptest.catmart.product.dto;

import com.shoptest.catmart.product.type.ItemStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * FRONT 조회용 상품 상세 dto
 * */

@Getter
@Setter
@ToString
public class FrontProductDetailDto {

  /* 상품 카테고리 ID */
  private Long categoryId;

  /* 상품 카테고리명 */
  private String categoryName;

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

  /* 상품 게시 여부 */
  private boolean postYn;

  /* 이미지 url 경로 */
  private String urlImgPath;

}
