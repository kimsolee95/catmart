package com.shoptest.catmart.product.dto;

import com.shoptest.catmart.product.type.ItemStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="ProductItemDto", description = "상품 정보 input DTO")
public class ProductItemDto {

  /* 상품 카테고리 ID */
  @ApiModelProperty(value="상품 카테고리 ID")
  @Positive(message = "유효하지 않은 카테고리값입니다.")
  private Long categoryId;

  /* 상품이름 */
  @ApiModelProperty(value="상품명")
  @NotBlank(message = "상품이름은 상품 등록 시 필수입니다.")
  private String itemName;

  /* 상품가격 */
  @ApiModelProperty(value="상품 가격")
  @Positive(message = "상품값은 0원 이상일 때만 상품 등록 가능합니다.")
  private int price;

  /* 상품 상태 - (판매중, 품절) */
  @ApiModelProperty(value="상품상태 [SAIL, OUT_OF_STOCK]")
  private ItemStatus itemStatus;

  /* 상품내용 */
  @ApiModelProperty(value="상품 내용")
  @NotBlank(message = "상품 내용은 상품 등록 시 필수입니다.")
  private String itemSubject;

  /* 상품재고 */
  @ApiModelProperty(value="상품 재고")
  @Min(value = 5, message = "상품 첫 등록 시, 상품의 재고는 5개 이상이어야 합니다.")
  private int stock;

  /* 상품 게시 여부 */
  @ApiModelProperty(value="상품 게시 여부")
  private boolean postYn;


}
