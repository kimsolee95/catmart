package com.shoptest.catmart.order.domain;

import com.shoptest.catmart.cart.dto.CartItemDetailDto;
import com.shoptest.catmart.member.domain.Member;
import com.shoptest.catmart.order.dto.OrderItemAddInputDto;
import com.shoptest.catmart.order.type.OrderStatus;
import com.shoptest.catmart.product.domain.ProductItem;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 주문 도메인
 * */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long ordersId;

  //foreign key로 memberId 지정하여 컬럼 값 생성
  //(회원 -> 주문 1:N || 회원 1명당 여러 개의 주문을 생성할 수 있음)
  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  //(주문 -> 주문_상품 1:N || 주문 1개당 여러 개의 주문상품이 생성될 수 있음)
  //fk 컬럼 자체(orders_id)는 OrdersItem 클래스 내에 있기 때문에, mappedBy 를 이용해서 주문 엔티티가 이 연관관계의 주인임을 설정해야 한다.
  //주문하기는 주문이라는 부모 data가 db 저장될 때, 주문상품이라는 자식 data도 부모와 함께 한 방에 저장되어야 한다. 따라서, cascade = CascadeType.ALL 속성이 필요.
  @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
  private List<OrdersItem> ordersItemList = new ArrayList<>();

  /* 주문 상태 종류 - (주문 접수(결제 전) / 주문 취소/ 결제완료) */
  @Enumerated(EnumType.STRING)
  private OrderStatus ordersStatus;

  /* 주문 일시 */
  private LocalDateTime ordersDate;

  /* 등록일자 */
  private LocalDateTime createdAt;

  /* 수정일자 */
  private LocalDateTime modifiedAt;

  /* 주문_상품 생성하기 - 장바구니에서 접근 */
  public OrdersItem createOrdersItem(ProductItem productItem, CartItemDetailDto toBeOrderItems) {

    OrdersItem ordersItem = OrdersItem.builder()
        .orders(this)
        .quantity(toBeOrderItems.getQuantity())
        .productItem(productItem)
        .orderPrice(productItem.getPrice() * toBeOrderItems.getQuantity())
        .createdAt(LocalDateTime.now())
        .build();
    return ordersItem;
  }

  /* 주문_상품 생성하기 - 단일 상품 바로 주문하기에서 접근 */
  public OrdersItem createOrdersItem(ProductItem productItem, OrderItemAddInputDto orderItemAddInputDto) {

    OrdersItem ordersItem = OrdersItem.builder()
        .orders(this)
        .quantity(orderItemAddInputDto.getQuantity())
        .productItem(productItem)
        .orderPrice(productItem.getPrice() * orderItemAddInputDto.getQuantity())
        .createdAt(LocalDateTime.now())
        .build();
    return ordersItem;
  }

  /* 주문 생성하기 */
  public Orders createOrders(Member member, Orders orders, List<OrdersItem> ordersItemList) {

    orders.setMember(member);
    orders.setOrdersItemList(ordersItemList);
    orders.setOrdersStatus(OrderStatus.TAKE_ORDER);
    orders.setOrdersDate(LocalDateTime.now());
    orders.setCreatedAt(LocalDateTime.now());
    return orders;
  }

  /* 주문 취소하기 */
  public Orders cancelOrders(Orders orders) {
    orders.setOrdersStatus(OrderStatus.CANCEL_ORDER);
    return orders;
  }


}
