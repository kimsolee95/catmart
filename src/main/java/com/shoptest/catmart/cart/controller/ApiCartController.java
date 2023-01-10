package com.shoptest.catmart.cart.controller;

import com.shoptest.catmart.cart.dto.CartItemAddInputDto;
import com.shoptest.catmart.cart.dto.CartItemDeleteInputDto;
import com.shoptest.catmart.cart.dto.CartItemUpdateInputDto;
import com.shoptest.catmart.cart.service.CartService;
import com.shoptest.catmart.common.model.ResponseResult;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiCartController {

  private final CartService cartService;

  //장바구니 상품 담기
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

  //장바구니 내역 조회 - 장바구니 상품 수량 변경
  @PutMapping("/api/cart/update-quantity-req")
  public ResponseEntity<?> cartProductQuantityUpdate(
      Model model
      , @RequestBody CartItemUpdateInputDto parameter
      , Principal principal) {

    String email = principal.getName();
    Long updatedCartItemId = cartService.updateItemQuantityInCart(email, parameter);

    if (updatedCartItemId == null) {
      ResponseResult responseResult = new ResponseResult(false);
      return ResponseEntity.ok().body(responseResult);
    }

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

  //장바구니 내역 조회 - 장바구니 상품 삭제
  @DeleteMapping("/api/cart/delete-product-req")
  public ResponseEntity<?> cartProductDelete(
      Model model
      , @RequestBody CartItemDeleteInputDto parameter
      , Principal principal) {

    String email = principal.getName();

    Long deletedCartItemId = cartService.deleteItemInCart(email, parameter);
    if (deletedCartItemId == null) {
      ResponseResult responseResult = new ResponseResult(false);
      return ResponseEntity.ok().body(responseResult);
    }

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

}
