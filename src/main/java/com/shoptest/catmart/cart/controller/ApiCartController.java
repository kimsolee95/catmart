package com.shoptest.catmart.cart.controller;

import com.shoptest.catmart.cart.dto.CartItemAddInputDto;
import com.shoptest.catmart.cart.dto.CartItemDeleteInputDto;
import com.shoptest.catmart.cart.dto.CartItemUpdateInputDto;
import com.shoptest.catmart.cart.service.CartService;
import com.shoptest.catmart.common.model.ResponseResult;
import io.swagger.annotations.ApiOperation;
import java.security.Principal;
import javax.validation.Valid;
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
public class ApiCartController {

  private final CartService cartService;

  @ApiOperation(value="장바구니 - 로그인한 고객의 장바구니에 장바구니 상품을 담습니다.", notes = "장바구니 상품 data isnert or update")
  @PostMapping("/api/cart/cartItem")
  public ResponseEntity<?> addCartItem(Model model, @RequestBody @Valid CartItemAddInputDto parameter, Principal principal) {

    String email = principal.getName();
    Long savedCartItemId = cartService.addItemInCart(email, parameter);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

  @ApiOperation(value="장바구니 - 로그인한 고객의 장바구니 내 상품 수량을 변경합니다.", notes ="장바구니 상품 수량 update")
  @PutMapping("/api/cart/cartItem")
  public ResponseEntity<?> updateCartProductQuantity(Model model, @RequestBody @Valid CartItemUpdateInputDto parameter, Principal principal) {

    String email = principal.getName();
    Long updatedCartItemId = cartService.updateItemQuantityInCart(email, parameter);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }


  @ApiOperation(value="장바구니 - 로그인한 고객의 장바구니 내 상품을 삭제합니다.", notes="장바구니 상품 delete")
  @DeleteMapping("/api/cart/cartItem/{cartItemId}")
  public ResponseEntity<?> deleteCartProduct(Model model, @PathVariable("cartItemId") Long cartItemId, Principal principal) {

    String email = principal.getName();

    Long deletedCartItemId = cartService.deleteItemInCart(email, cartItemId);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

}
