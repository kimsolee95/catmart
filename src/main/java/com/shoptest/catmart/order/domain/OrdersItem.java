package com.shoptest.catmart.order.domain;

import com.shoptest.catmart.product.domain.ProductItem;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 주문_상품 도메인
 * */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long ordersItemId;

  //foreign key로 orders_id 지정하여 컬럼 값 생성
  //(주문 -> 주문_상품 1:N || 주문 1개당 여러 개의 주문상품이 생성될 수 있음)
  //현재 주문_상품 엔티티에 orders_id(주문 pk)가 외래키로 존재하고 있는데,
  // orders 엔티티 클래스에서 mappedBy 속성을 이용해서 orders 엔티티가 이 연관관계(orders - orders_item 간의 양방향 연관 관계) 간의 주인임을 설정해야 한다.
  // (Orders.java 47 line 참고)
  @ManyToOne
  @JoinColumn(name = "orders_id")
  private Orders orders;

  /* 주문_상품 상품개수 */
  private int quantity;

  //foreign key로 product_item_id 지정하여 컬럼 값 생성
  //(상품 -> 주문_상품 1:N || 상품 table 1개의 record가 주문_상품의 다수의 record 내 필드로 쓰일 수 있음)
  @ManyToOne
  @JoinColumn(name = "product_item_id")
  private ProductItem productItem;

  /* 주문_상품 결제금액(주문 금액) */
  private int orderPrice;

  /* 등록일자 */
  private LocalDateTime createdAt;

  /* 수정일자 */
  private LocalDateTime modifiedAt;


}
