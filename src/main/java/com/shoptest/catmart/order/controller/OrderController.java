package com.shoptest.catmart.order.controller;

import com.shoptest.catmart.cart.service.CartService;
import com.shoptest.catmart.member.service.MemberService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

  private final CartService cartService;
  private final MemberService memberService;

  @GetMapping("/cartItem")
  public String orderFromCart(Model model, Principal principal) {

    String email = principal.getName();
    model.addAttribute("shippingAddress", memberService.selectMemberAddress(email)); //배송지 (원래는 배송 관련 내용만 따로 담은 table 필요..)
    model.addAttribute("cartItemList", cartService.selectCartItemDetailList(email)); //장바구니에 담은 상품 목록

    return "/order/order_from_cart";
  }

}
