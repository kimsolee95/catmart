package com.shoptest.catmart.order.controller.api;

import com.shoptest.catmart.common.model.ResponseResult;
import com.shoptest.catmart.order.dto.OrderItemAddInputDto;
import com.shoptest.catmart.order.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.security.Principal;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
  @ApiResponses({
      @ApiResponse(code=200, message="로그인한 회원의 장바구니 상품 전체주문 완료"),
      @ApiResponse(code=400, message="장바구니 상품 주문 중, 상품 재고 초과 등 비즈니스적 오류 시 예외 처리 실행"),
  })
  @ApiOperation(value="장바구니 상품 전체 주문 API", notes = "로그인한 고객의 장바구니 정보를 조회하여 장바구니 내 상품을 일괄 주문 후, 장바구니 상품을 삭제 처리합니다.")
  public ResponseEntity<?> createOrderFromCart(Model model, Principal principal) {

    String email = principal.getName();
    orderService.createOrder(email);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

  @PostMapping("/api/order/singleProduct")
  @ApiResponses({
      @ApiResponse(code=200, message="로그인한 회원의 상품 개별 주문 완료"),
      @ApiResponse(code=400, message="상품 주문 중, 상품 재고 초과 등 비즈니스적 오류 시 예외 처리 실행"),
  })
  @ApiOperation(value="단일 상품 개별 주문 API", notes = "로그인한 고객이 상품 상세 보기 내에서 상품 번호를 통해 주문을 합니다.")
  public ResponseEntity<?> createOrderFromProductDetail(Model model, Principal principal, @RequestBody @Valid OrderItemAddInputDto orderItemAddInputDto) {

    String email = principal.getName();
    orderService.createOrder(orderItemAddInputDto, email);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

  @PutMapping("/api/order/orderItem/{ordersId}")
  @ApiResponses({
      @ApiResponse(code=200, message="주문 취소 완료"),
      @ApiResponse(code=400, message="이미 주문 취소하거나, 주문 취소 불가능(결제 완료) 상태 등 비즈니스적 오류 시 예외 처리 실행"),
  })
  @ApiOperation(value="주문 취소 API", notes = "로그인한 고객이 자신의 주문에 한해 주문을 취소할 수 있는 API입니다. 주문 상태를 주문 취소로 변경합니다.")
  public ResponseEntity<?> cancelOrderForMember(Model model, Principal principal, @PathVariable Long ordersId) {

    String email = principal.getName();
    orderService.cancelOrder(email, ordersId);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

}
