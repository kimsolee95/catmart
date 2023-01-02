package com.shoptest.catmart.admin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 카테고리 front main page 조회용 dto
 * */
@Getter
@Setter
@ToString
public class FrontCategoryDto {

  /* 카테고리 id */
  private Long categoryId;

  /* 카테고리명 */
  private String categoryName;

  /* 카테고리 이미지 url path */
  private String urlImgPath;

}
