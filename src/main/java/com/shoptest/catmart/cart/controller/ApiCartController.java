package com.shoptest.catmart.cart.controller;

import com.shoptest.catmart.cart.dto.CartItemAddInputDto;
import com.shoptest.catmart.cart.service.CartService;
import com.shoptest.catmart.common.model.ResponseResult;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiCartController {

  private final CartService cartService;

  @PostMapping("/api/cart/add-req")
  public ResponseEntity<?> cartAddReq(
      Model model
      , @RequestBody CartItemAddInputDto parameter
      , Principal principal) {

    String email = principal.getName();
    Long savedCartItemId = cartService.addItemInCart(email, parameter);

    if (savedCartItemId == null) {
      ResponseResult responseResult = new ResponseResult(false);
      return ResponseEntity.ok().body(responseResult);
    }

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

}
