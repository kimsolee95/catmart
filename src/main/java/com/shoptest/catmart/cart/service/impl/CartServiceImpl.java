package com.shoptest.catmart.cart.service.impl;

import com.shoptest.catmart.cart.domain.Cart;
import com.shoptest.catmart.cart.domain.CartItem;
import com.shoptest.catmart.cart.dto.CartItemAddInputDto;
import com.shoptest.catmart.cart.repository.CartItemRepository;
import com.shoptest.catmart.cart.repository.CartRepository;
import com.shoptest.catmart.cart.service.CartService;
import com.shoptest.catmart.member.domain.Member;
import com.shoptest.catmart.member.repository.MemberRepository;
import com.shoptest.catmart.product.domain.ProductItem;
import com.shoptest.catmart.product.repository.ProductItemRepository;
import java.time.LocalDateTime;
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

  /**
   * @return 장바구니 상품 ID, Long CartItemId
   *
   * */
  @Transactional
  @Override
  public Long addItemInCart(String email, CartItemAddInputDto parameter) {

    //1. member data check
    Optional<Member> optionalMember = memberRepository.findByEmail(email);
    if (!optionalMember.isPresent()) {
      //exception 처리 필요 -> 로그인 정보(email) 로 찾은 member data가 없을 때의 예외 처리
      return null;
    }
    Member member = optionalMember.get();

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
    ProductItem wishProductItem = optionalProductItem.get(); //특정 상품상세 page 내에서 쓰이는 api이기 때문에 상품 ID에 대한 상품 value는 항상 있다고 가정?.. 보단 exception 추가 필요.

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





}
