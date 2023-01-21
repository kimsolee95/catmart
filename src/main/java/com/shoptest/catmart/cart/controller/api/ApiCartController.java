package com.shoptest.catmart.cart.controller.api;

import com.shoptest.catmart.cart.dto.CartItemAddInputDto;
import com.shoptest.catmart.cart.dto.CartItemDeleteInputDto;
import com.shoptest.catmart.cart.dto.CartItemUpdateInputDto;
import com.shoptest.catmart.cart.service.CartService;
import com.shoptest.catmart.common.model.ResponseResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

  @PostMapping("/api/cart/cartItem")
  @ApiResponses({
      @ApiResponse(code=200, message="장바구니 상품 담기 완료"),
      @ApiResponse(code=400, message="비즈니스적 오류 시 예외 처리 실행"),
  })
  @ApiOperation(value="장바구니 상품 담기 API", notes = "로그인한 고객의 장바구니에 장바구니 상품을 담습니다.")
  public ResponseEntity<?> addCartItem(Model model, @RequestBody @Valid CartItemAddInputDto parameter, Principal principal) {

    String email = principal.getName();
    Long savedCartItemId = cartService.addItemInCart(email, parameter);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

  @PutMapping("/api/cart/cartItem")
  @ApiResponses({
      @ApiResponse(code=200, message="장바구니 상품 수량 변경 완료"),
      @ApiResponse(code=400, message="마켓 내 상품 재고 초과하는 장바구니 상품 수량 등 비즈니스적 오류 시 예외 처리 실행"),
  })
  @ApiOperation(value="장바구니 상품 수량 변경 API", notes ="로그인한 고객에 한하여 장바구니 상품 수량을 변경할 수 있고, 수량은 0보다 크되, 상품 재고수량보다 적을 때만 변경 가능합니다.")
  public ResponseEntity<?> updateCartProductQuantity(Model model, @RequestBody @Valid CartItemUpdateInputDto parameter, Principal principal) {

    String email = principal.getName();
    Long updatedCartItemId = cartService.updateItemQuantityInCart(email, parameter);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }


  @DeleteMapping("/api/cart/cartItem/{cartItemId}")
  @ApiResponses({
      @ApiResponse(code=200, message="장바구니 상품 삭제 완료"),
      @ApiResponse(code=400, message="비즈니스적 오류 시 예외 처리 실행"),
  })
  @ApiOperation(value="장바구니 상품 삭제 API", notes="로그인한 고객에 한하여 장바구니 상품을 삭제할 수 있습니다.")
  public ResponseEntity<?> deleteCartProduct(Model model, @PathVariable("cartItemId") Long cartItemId, Principal principal) {

    String email = principal.getName();

    Long deletedCartItemId = cartService.deleteItemInCart(email, cartItemId);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

}
