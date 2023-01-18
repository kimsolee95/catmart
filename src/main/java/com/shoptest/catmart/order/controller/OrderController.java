package com.shoptest.catmart.order.controller;

import com.shoptest.catmart.cart.service.CartService;
import com.shoptest.catmart.member.service.MemberService;
import com.shoptest.catmart.order.dto.OrderItemAddInputDto;
import com.shoptest.catmart.order.service.OrderService;
import com.shoptest.catmart.product.domain.ProductItem;
import com.shoptest.catmart.product.service.ProductService;
import java.net.http.HttpRequest;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

  private final CartService cartService;
  private final MemberService memberService;
  private final OrderService orderService;
  private final ProductService productService;

  /* 장바구니 -> 주문서 페이지 */
  @GetMapping("/cartItem")
  public String orderFromCart(Model model, Principal principal) {

    String email = principal.getName();
    model.addAttribute("shippingAddress", memberService.selectMemberAddress(email)); //배송지 (원래는 배송 관련 내용만 따로 담은 table 필요..)
    model.addAttribute("cartItemList", cartService.selectCartItemDetailList(email)); //장바구니에 담은 상품 목록

    return "/order/order_from_cart";
  }

  @GetMapping("/product")
  public String orderFromProduct(
      Model model, Principal principal, OrderItemAddInputDto orderItemAddInputDto) {

    String email = null;
    if (principal != null) {
      email = principal.getName();
    }

    model.addAttribute("email", email);
    model.addAttribute("shippingAddress", memberService.selectMemberAddress(email)); //배송지 (원래는 배송 관련 내용만 따로 담은 table 필요..)
    model.addAttribute("toBeOrderItem", productService.selectOrderItemDetailByProduct(orderItemAddInputDto));

    return "/order/order_from_product";
  }

  @GetMapping("/history")
  public String orderHistory(Model model, Principal principal) {

    String email = principal.getName();
    model.addAttribute("ordersHistoryList", orderService.selectOrdersHistoryList(email));

    return "/order/order_history_list";
  }

  @GetMapping("/history/detail/{ordersId}")
  public String odderHistoryDetail(Model model, Principal principal, @PathVariable Long ordersId) {

    String email = principal.getName();
    model.addAttribute("shippingAddress", memberService.selectMemberAddress(email)); //배송지 (원래는 배송 관련 내용만 따로 담은 table 필요..)
    model.addAttribute("ordersHistoryDetailList", orderService.selectOrdersHistoryDetailList(email, ordersId)); //주문 내 상세 주문정보 list

    return "/order/order_history_detail";
  }

}
