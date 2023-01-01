package com.shoptest.catmart.admin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 카테고리 select box dto
 * */
@Getter
@Setter
@ToString
public class CategorySelectionDto {

  /* 카테고리 번호 */
  private Long categoryId;

  /* 카테고리명 */
  private String categoryName;


}
