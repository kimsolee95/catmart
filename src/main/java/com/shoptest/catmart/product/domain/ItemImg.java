package com.shoptest.catmart.product.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * 상품 이미지 도메인
 * */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemImg {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long imgId;

  /* 이미지명 */
  private String imgName;

  /* 이미지저장 원 경로 */
  private String originImgPath;

  /* 이미지 url 경로 */
  private String urlImgPath;

  /* 상품 id */
  private Long productItemId;

  /* 등록일자 */
  private LocalDateTime createdAt;

  /* 수정일자 */
  private LocalDateTime modifiedAt;

}
