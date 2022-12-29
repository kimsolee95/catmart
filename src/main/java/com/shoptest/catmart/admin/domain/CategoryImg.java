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
 * 카테고리 이미지 도메인
 * */
@Entity
@Getter
@Setter
@ToString
public class CategoryImg {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long imgId;

  /* 이미지명 */
  private String imgName;

  /* 이미지저장 원 경로 */
  private String originImgPath;

  /* 이미지 url 경로 */
  private String urlImgPath;

  /* 카테고리 id */
  private Long categoryId;

  /* 등록일자 */
  private LocalDateTime createdAt;

  /* 수정일자 */
  private LocalDateTime modifiedAt;

}
