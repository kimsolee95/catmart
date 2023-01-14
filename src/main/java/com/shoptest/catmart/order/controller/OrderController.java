package com.shoptest.catmart.order.controller;

import com.shoptest.catmart.cart.service.CartService;
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

  @GetMapping
  public String placeAnOrderForm(Model model, Principal principal) {

    String email = principal.getName();
    model.addAttribute("cartItemList", cartService.selectCartItemDetailList(email));

    return "/order/place_an_order";
  }

}
