package com.shoptest.catmart.cart.service.impl;

import com.shoptest.catmart.cart.domain.Cart;
import com.shoptest.catmart.cart.domain.CartItem;
import com.shoptest.catmart.cart.dto.CartItemAddInputDto;
import com.shoptest.catmart.cart.dto.CartItemDeleteInputDto;
import com.shoptest.catmart.cart.dto.CartItemDetailDto;
import com.shoptest.catmart.cart.dto.CartItemUpdateInputDto;
import com.shoptest.catmart.cart.mapper.CartMapper;
import com.shoptest.catmart.cart.repository.CartItemRepository;
import com.shoptest.catmart.cart.repository.CartRepository;
import com.shoptest.catmart.cart.service.CartService;
import com.shoptest.catmart.common.exception.CartException;
import com.shoptest.catmart.common.exception.type.CartErrorCode;
import com.shoptest.catmart.member.domain.Member;
import com.shoptest.catmart.member.repository.MemberRepository;
import com.shoptest.catmart.product.domain.ProductItem;
import com.shoptest.catmart.product.repository.ProductItemRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final MemberRepository memberRepository;
  private final ProductItemRepository productItemRepository;
  private final CartMapper cartMapper;

  /**
   * @return 장바구니 담기 완료 상품 ID, Long CartItemId
   *
   * */
  @Transactional
  @Override
  public Long addItemInCart(String email, CartItemAddInputDto parameter) {

    //1. member data check
    Member member = getMember(email);

    //2. member`s cart data check
    //회원 - 장바구니 (1:1) 이므로 member`s cart가 없다면 create cart 메서드 실행
    Cart cartOfMember = cartRepository.findByMemberMemberId(member.getMemberId())
        .orElseGet(() -> createNewCart(member));

    //3. cartItem data check (by FK (cartId, productItemId))
    ProductItem wishProductItem = productItemRepository.findById(parameter.getProductItemId())
        .orElseThrow(() -> new CartException(CartErrorCode.PRODUCT_ITEM_NOT_EXIST));

    //product data check
    //주문 확인서에서 수량 바꾸기 불가능함. 이용자 편의성 위해 처음 장바구니에서 수량 update 이벤트 발생 시, 재고 이상으로 수량 담는 것이 불가능하게끔 익셉션 처리 추가
    if (wishProductItem.getStock() < parameter.getQuantity()) {
      throw new CartException(CartErrorCode.OVER_QUANTITY);
    }

    Optional<CartItem> optionalCartItem = cartItemRepository.findByCartCartIdAndProductItemProductItemId(
        cartOfMember.getCartId(), parameter.getProductItemId());

    if (!optionalCartItem.isPresent()) {

      //상품 ID와 장바구니 ID 일치하는 data가 없다면 새로운 장바구니상품이므로 create.
      CartItem newCartItem = CartItem.builder()
          .cart(cartOfMember)
          .productItem(wishProductItem)
          .quantity(parameter.getQuantity())
          .createdAt(LocalDateTime.now())
          .build();
      cartItemRepository.save(newCartItem);
      return newCartItem.getCartItemId();

    } else {

      //상품 ID와 장바구니 ID 일치 data가 있다면 기존에 insert된 장바구니상품이므로 기존 data에서 수량 update.
      CartItem existCartItem = optionalCartItem.get();
      existCartItem.addQuantity(parameter.getQuantity());
      cartItemRepository.save(existCartItem);
      return existCartItem.getCartItemId();
    }
  }


  @Override
  public List<CartItemDetailDto> selectCartItemDetailList(String email) {

    //1. member data
    Member member = getMember(email);
    return cartMapper.selectCartItemDetailList(member.getMemberId());
  }

  @Transactional
  @Override
  public Long updateItemQuantityInCart(String email, CartItemUpdateInputDto parameter) {

    //product data check
    //주문 확인서에서 수량 바꾸기 불가능함. 이용자 편의성 위해 처음 장바구니에서 수량 update 이벤트 발생 시, 재고 이상으로 수량 담는 것이 불가능하게끔 익셉션 처리 추가
    if (parameter.getQuantity() > parameter.getStock()) {
      throw new CartException(CartErrorCode.OVER_QUANTITY);
    }

    //1. member data check
    Member member = getMember(email);

    //2. cart data check
    validateCartExistence(member);

    //3. cartItem update
    CartItem cartItem = cartItemRepository.findById(parameter.getCartItemId())
            .orElseThrow(() -> new CartException(CartErrorCode.USER_CART_ITEM_NOT_EXIST));

    cartItem.setQuantity(parameter.getQuantity());
    cartItem.setModifiedAt(LocalDateTime.now());
    cartItemRepository.save(cartItem);

    return cartItem.getCartItemId();
  }

  @Transactional
  @Override
  public Long deleteItemInCart(String email, CartItemDeleteInputDto parameter) {

    //member data check
    Member member = getMember(email);

    //cart data check
    Cart cart = validateCartExistence(member);

    //cartItem data delete
    return cartItemRepository.deleteByCartCartIdAndCartItemId(cart.getCartId(), parameter.getCartItemId());
  }

  private Member getMember(String email) {

    return memberRepository.findByEmail(email)
        .orElseThrow(() -> new CartException(CartErrorCode.USER_EMAIL_NOT_EXIST));
  }

  /**
   * 회원 - 장바구니 (1:1) 이므로 cart가 없다면  create cart
   * */
  private Cart createNewCart(Member member) {

      Cart newCart = Cart.builder()
          .member(member)
          .createdAt(LocalDateTime.now())
          .build();
    return cartRepository.save(newCart);
  }

  /**
   * 장바구니 상품 data update or delete 시, 해당 회원의 장바구니 데이터 validation check
   * */
  private Cart validateCartExistence(Member member) {

    return cartRepository.findByMemberMemberId(member.getMemberId())
        .orElseThrow(() -> new CartException(CartErrorCode.USER_CART_NOT_EXIST));
  }

}
