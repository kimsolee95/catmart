package com.shoptest.catmart.product.dto;

import com.shoptest.catmart.product.type.ItemStatus;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
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
  @Positive(message = "유효하지 않은 카테고리값입니다.")
  private Long categoryId;

  /* 상품이름 */
  @NotBlank(message = "상품이름은 상품 등록 시 필수입니다.")
  private String itemName;

  /* 상품가격 */
  @Positive(message = "상품값은 0원 이상일 때만 상품 등록 가능합니다.")
  private int price;

  /* 상품 상태 - (판매중, 품절) */
  private ItemStatus itemStatus;

  /* 상품내용 */
  @NotBlank(message = "상품 내용은 상품 등록 시 필수입니다.")
  private String itemSubject;

  /* 상품재고 */
  @Min(value = 5, message = "상품 첫 등록 시, 상품의 재고는 5개 이상이어야 합니다.")
  private int stock;

  /* 상품 게시 여부 */
  private boolean postYn;


}
