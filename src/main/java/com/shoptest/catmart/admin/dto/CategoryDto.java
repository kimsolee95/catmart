package com.shoptest.catmart.admin.dto;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 카테고리 dto
 * */

@Getter
@Setter
@ToString
public class CategoryDto {

  /* 카테고리명 */
  @NotBlank(message = "카테고리명을 입력해주세요.")
  private String categoryName;

  /* 등록일자 */
  private LocalDateTime createdAt;

  /* 수정일자 */
  private LocalDateTime modifiedAt;

}
