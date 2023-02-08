package com.shoptest.catmart.order.service.impl;

import com.shoptest.catmart.cart.domain.Cart;
import com.shoptest.catmart.cart.dto.CartItemDetailDto;
import com.shoptest.catmart.cart.repository.CartRepository;
import com.shoptest.catmart.cart.service.CartService;
import com.shoptest.catmart.common.exception.customexception.OrderException;
import com.shoptest.catmart.common.exception.type.OrderErrorCode;
import com.shoptest.catmart.member.domain.Member;
import com.shoptest.catmart.member.repository.MemberRepository;
import com.shoptest.catmart.order.domain.Orders;
import com.shoptest.catmart.order.domain.OrdersItem;
import com.shoptest.catmart.order.dto.OrderItemAddInputDto;
import com.shoptest.catmart.order.dto.OrdersHistoryDetailDto;
import com.shoptest.catmart.order.dto.OrdersHistoryDto;
import com.shoptest.catmart.order.mapper.OrdersMapper;
import com.shoptest.catmart.order.repository.OrdersRepository;
import com.shoptest.catmart.order.service.OrderService;
import com.shoptest.catmart.order.type.OrderStatus;
import com.shoptest.catmart.product.domain.ProductItem;
import com.shoptest.catmart.product.repository.ProductItemRepository;
import com.shoptest.catmart.product.type.ItemStatus;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository;
  private final ProductItemRepository productItemRepository;
  private final CartService cartService;
  private final CartRepository cartRepository;
  private final OrdersRepository ordersRepository;
  private final OrdersMapper ordersMapper;

  /* 고객 - 주문하기(장바구니 - 장바구니 내 모든 상픔 주문) */
  @Transactional
  @Override
  public void createOrder(String email) {

    //1. member check
    Member member = getMember(email);

    //2. to be orders_item -> check
    // (1) 해당 이용자 장바구니_상품 및 해당 상품 정보 select -> (2) 현재 상품 재고 및 상태 체크 -> (3) 주문_상품으로 변환하기 4) 주문에 주문 상품 set
    //2-1. 현재 이용자의 장바구니 상품 및 해당 상품 정보 모두 가져오기(myBatis 쿼리 직접 실행)
    List<CartItemDetailDto> cartItemDetailList = cartService.selectCartItemDetailList(email);
    if (cartItemDetailList.isEmpty()) {
      throw new OrderException(OrderErrorCode.NOT_EXIST_CART_ITEM);
    }

    List<OrdersItem> ordersItemList = new ArrayList<>(); //주문 객체 내에 들어갈 자식인 주문 상품 list
    Orders orders = new Orders(); //해당 메서드 내에서 save할 주문 객체를 미리 생성...

    for (CartItemDetailDto cartItem : cartItemDetailList) {
      //현재 장바구니 내 설정 수량이 재고 초과
      if (cartItem.getQuantity() > cartItem.getStock()) {
        throw new OrderException(OrderErrorCode.OVER_QUANTITY);
      }
      //상품품절 상태
      if (ItemStatus.OUT_OF_STOCK.equals(cartItem.getItemStatus())) {
        throw new OrderException(OrderErrorCode.OUT_OF_STOCK);
      }

      //3. orders_item create
      ProductItem productItem = productItemRepository.findById(cartItem.getProductItemId())
          .orElseThrow(() -> new OrderException(OrderErrorCode.NOT_EXIST_PRODUCT_ITEM));

      //부모인 주문 도메인이 직접 주문 상품 생성해서 반환하도록 변경 (이 메서드 내에서 생성 완료될 주문 객체가 주문 자식 내에도 있어야 해서 직접 주문 엔티티 내에서 this로 주문자식 객체에 본인을 setting하기로 함)
      OrdersItem ordersItem = orders.createOrdersItem(productItem, cartItem);
      ordersItemList.add(ordersItem);

      //해당 상품의 재고를 장바구니 내 상품 수량만큼 차감
      productItem.deductionStock(productItem, cartItem.getQuantity());
      productItemRepository.save(productItem);
    }

    //3. order set (item setting)
    orders = orders.createOrders(member, orders, ordersItemList); //수정함.. (빌더패턴으로 통일을 못했는데 통일 필요할 수도..)
    ordersRepository.save(orders); //주문 key값 안 들어가서 도메인이 직접 객체들을 만들어서 반환하는 식으로 수정.

    //4. 장바구니 상품 삭제 (한꺼번에 장바구니 내 아이템을 모두 주문 들어갔기 때문에, 이용자의 장바구니 id를 key로 해서 한번에 장바구니 상품 삭제 처리)
    Cart cart = cartRepository.findById(cartItemDetailList.get(0).getCartId())
            .orElseThrow(() -> new OrderException(OrderErrorCode.OVER_QUANTITY));
    cartService.deleteAllCartItem(cart.getCartId());
  }

  /* 고객 - 주문하기 (상품 상세 페이지 - 단일 상품 주문) */
  @Transactional
  @Override
  public void createOrder(OrderItemAddInputDto orderItemAddInputDto, String email) {

    //1. member check
    Member member = getMember(email);

    //2. productItem check
    ProductItem productItem = productItemRepository.findById(orderItemAddInputDto.getProductItemId())
        .orElseThrow(() -> new OrderException(OrderErrorCode.NOT_EXIST_PRODUCT_ITEM));
    //재고량 체크
    if (productItem.getStock() < orderItemAddInputDto.getQuantity()) {
      throw new OrderException(OrderErrorCode.OVER_QUANTITY);
    }
    //상품 상태(판매 or 품절) 체크
    if (ItemStatus.OUT_OF_STOCK.equals(productItem.getItemStatus())) {
      throw new OrderException(OrderErrorCode.OUT_OF_STOCK);
    }
    //상품 주문량만큼 재고수 차감
    productItem.deductionStock(productItem, orderItemAddInputDto.getQuantity());

    //3. orders_item create
    List<OrdersItem> ordersItemList = new ArrayList<>(); //주문 객체 내에 들어갈 자식인 주문 상품 list
    Orders orders = new Orders(); //해당 메서드 내에서 save할 주문 객체를 미리 생성...

    OrdersItem ordersItem = orders.createOrdersItem(productItem, orderItemAddInputDto);
    ordersItemList.add(ordersItem);

    //4. order create
    orders.createOrders(member, orders, ordersItemList);

    //5. save data
    productItemRepository.save(productItem);
    ordersRepository.save(orders);
  }

  @Override
  public List<OrdersHistoryDto> selectOrdersHistoryList(String email) {

    Member member = getMember(email);
    return ordersMapper.selectOrdersHistoryList(member.getMemberId());
  }

  @Override
  public List<OrdersHistoryDetailDto> selectOrdersHistoryDetailList(String email, Long ordersId) {

    Member member = getMember(email);
    return ordersMapper.selectOrdersHistoryDetailList(member.getMemberId(), ordersId);
  }

  @Transactional
  @Override
  public void cancelOrder(String email, Long ordersId) {

    Member member = getMember(email);
    Orders orders = ordersRepository.findById(ordersId)
        .orElseThrow(() -> new OrderException(OrderErrorCode.NOT_EXIST_ORDER));

    //주문상태가 주문 접수인 경우에만 주문 취소하도록 한다.
    if (!OrderStatus.TAKE_ORDER.equals(orders.getOrdersStatus())) {
      throw new OrderException(OrderErrorCode.NOT_POSSIBLE_CANCEL);
    }

    //jpa에서 select해서(member table과 outer join) 비교하기 때문에 취소상태인 것에 대한 재 취소 요청은 update 쿼리는 생성되지 않는다
    orders = orders.cancelOrders(orders); //?? 확인 필요..

    //주문 시 상품 재고 차감이 이뤄지기 때문에, 주문취소한다면 주문상품의 주문수량만큼 다시 상품 재고에 더한다.
    List<OrdersItem> cancelOrdersItemList = orders.getOrdersItemList();
    for (OrdersItem item : cancelOrdersItemList) {

      int itemQuantity = item.getQuantity(); //주문 상품 수량
      //상품 수량에 주문 상품 수량 다시 더하기
      ProductItem productItem = new ProductItem();
      productItem = productItem.addStock(item.getProductItem(), itemQuantity);
      //더한 상품값 반영한 상품 객체를 주문상품에 set
      item.setProductItem(productItem);
    }

    orders.setOrdersItemList(cancelOrdersItemList);
    ordersRepository.save(orders);
  }

  private Member getMember(String email) {

    Member member = memberRepository.findByEmail(email)
        .orElseThrow(() -> new OrderException(OrderErrorCode.USER_EMAIL_NOT_EXIST));
    return member;
  }

}
