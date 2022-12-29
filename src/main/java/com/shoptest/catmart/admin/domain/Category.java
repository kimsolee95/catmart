package com.shoptest.catmart.admin.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 카테고리 도메인
 * */
@Entity
@Getter
@Setter
@ToString
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long categoryId;

  /* 카테고리명 */
  private String categoryName;

  /* 등록일자 */
  private LocalDateTime createdAt;

  /* 수정일자 */
  private LocalDateTime modifiedAt;

}
