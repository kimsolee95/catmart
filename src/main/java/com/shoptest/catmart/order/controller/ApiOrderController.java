package com.shoptest.catmart.order.controller;

import com.shoptest.catmart.common.model.ResponseResult;
import com.shoptest.catmart.order.service.OrderService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiOrderController {

  private final OrderService orderService;

  @PostMapping("/api/order/from-cart")
  public ResponseEntity<?> createOrderFromCart(Model model, Principal principal) {

    String email = principal.getName();
    orderService.createOrder(email);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

}
