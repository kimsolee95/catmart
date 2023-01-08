package com.shoptest.catmart.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

  @GetMapping("/list")
  public String cartItemList(Model model) {

    return "/cart/cart_item_list";
  }

}
