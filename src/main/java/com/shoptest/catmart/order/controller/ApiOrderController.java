package com.shoptest.catmart.order.controller;

import com.shoptest.catmart.common.model.ResponseResult;
import com.shoptest.catmart.order.dto.OrderItemAddInputDto;
import com.shoptest.catmart.order.service.OrderService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiOrderController {

  private final OrderService orderService;

  @PostMapping("/api/order/cartItem")
  public ResponseEntity<?> createOrderFromCart(Model model, Principal principal) {

    String email = principal.getName();
    orderService.createOrder(email);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

  @PostMapping("/api/order/singleProduct")
  public ResponseEntity<?> createOrderFromProductDetail(Model model, Principal principal, @RequestBody OrderItemAddInputDto orderItemAddInputDto) {

    String email = principal.getName();
    orderService.createOrder(orderItemAddInputDto, email);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

  @PutMapping("/api/order/orderItem/{ordersId}")
  public ResponseEntity<?> cancelOrderForMember(Model model, Principal principal, @PathVariable Long ordersId) {

    String email = principal.getName();
    orderService.cancelOrder(email, ordersId);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

}
