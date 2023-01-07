package com.shoptest.catmart.cart.domain;

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
 * 장바구니_상품 도메인
 * */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long cartItemId;

  /* 장바구니 도메인 foreign key */
  @ManyToOne
  @JoinColumn(name = "cart_id")
  private Cart cart;

  /* 상품 도메인 foreign key */
  @ManyToOne
  @JoinColumn(name = "product_item_id")
  private ProductItem productItem;

  /* 장바구니 상품개수 */
  private int quantity;

  /* 등록일자 */
  private LocalDateTime createdAt;

  /* 수정일자 */
  private LocalDateTime modifiedAt;
}
