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
    Cart cartOfMember = null;
    Optional<Cart> optionalCart = cartRepository.findByMemberMemberId(member.getMemberId());

    if (!optionalCart.isPresent()) {

      //회원 - 장바구니 (1:1) 이므로 create cart
      Cart newCart = Cart.builder()
          .member(member)
          .createdAt(LocalDateTime.now())
          .build();
      cartOfMember = cartRepository.save(newCart);
    } else {

      cartOfMember = optionalCart.get();
    }

    //3. cartItem data check (by FK (cartId, productItemId))
    Optional<ProductItem> optionalProductItem = productItemRepository.findById(parameter.getProductItemId());
    if (!optionalProductItem.isPresent()) {
      throw new CartException(CartErrorCode.PRODUCT_ITEM_NOT_EXIST);
    }
    ProductItem wishProductItem = optionalProductItem.get();

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

    //1. member data check
    Member member = getMember(email);

    //2. cart data check
    Optional<Cart> optionalCart = cartRepository.findByMemberMemberId(member.getMemberId());
    if (!optionalCart.isPresent()) {
      throw new CartException(CartErrorCode.USER_CART_NOT_EXIST);
    }

    //3. product data check
    //?? 장바구니는 수량 체크할 필요 없고 주문에서만 체크하도록 요구사항을 정하는 것이 통상적인 rule인지 아니면 장바구니도 수량 체크 - 재고 비교 해야 하는지... 확인 필요

    //4. cartItem update
    Optional<CartItem> optionalCartItem = cartItemRepository.findById(parameter.getCartItemId());
    if (!optionalCartItem.isPresent()) {
      throw new CartException(CartErrorCode.USER_CART_ITEM_NOT_EXIST);
    }
    CartItem cartItem = optionalCartItem.get();
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
    Optional<Cart> optionalCart = cartRepository.findByMemberMemberId(member.getMemberId());
    if (!optionalCart.isPresent()) {
      throw new CartException(CartErrorCode.USER_CART_NOT_EXIST);
    }
    Cart cart = optionalCart.get();

    //cartItem data delete
    Long result = cartItemRepository.deleteByCartCartIdAndCartItemId(cart.getCartId(), parameter.getCartItemId());
    return result;
  }

  private Member getMember(String email) {
    return memberRepository.findByEmail(email)
        .orElseThrow(() -> new CartException(CartErrorCode.USER_EMAIL_NOT_EXIST));
  }


}
