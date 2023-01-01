package com.shoptest.catmart.product.domain;

import com.shoptest.catmart.product.type.ItemStatus;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
 * 상품 도메인
 * */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long productItemId;

  /* 상품 카테고리 ID */
  private Long categoryId;

  /* 상품이름 */
  private String itemName;
  
  /* 상품가격 */
  private int price;
  
  /* 상품 상태 - (판매중, 품절) */
  @Enumerated(EnumType.STRING)
  private ItemStatus itemStatus;
  
  /* 상품내용 */
  private String itemSubject;
  
  /* 상품재고 */
  private int stock;
  
  /* 등록일자 */
  private LocalDateTime createdAt;
  
  /* 수정일자 */
  private LocalDateTime modifiedAt;
  
}
