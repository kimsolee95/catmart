package com.shoptest.catmart.cart.controller;

import com.shoptest.catmart.cart.service.CartService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @GetMapping("/list")
  public String cartItemList(Model model, Principal principal) {

    String email = principal.getName();

    model.addAttribute("cartItemList", cartService.selectCartItemDetailList(email));

    return "cart/cart_item_list";
  }

}
