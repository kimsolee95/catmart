package com.shoptest.catmart.cart.dto;

import com.shoptest.catmart.product.type.ItemStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * front 장바구니 내역 - 장바구니 내역 조회용 DTO
 * */
@Getter
@Setter
public class CartItemDetailDto {

  /* 장바구니 상품 ID */
  private Long cartItemId;

  /* 장바구니 상품개수 */
  private int quantity;

  /* 장바구니 ID */
  private Long cartId;

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
