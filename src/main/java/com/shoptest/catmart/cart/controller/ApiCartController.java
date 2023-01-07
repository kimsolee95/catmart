package com.shoptest.catmart.cart.controller;

import com.shoptest.catmart.common.model.ResponseResult;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiCartController {


  @PostMapping("/api/cart/add-req")
  public ResponseEntity<?> cartAddReq(Model model
                                      , Principal principal) {

    ResponseResult responseResult = new ResponseResult(true);

    //add logic

    return ResponseEntity.ok().body(responseResult);
  }

}
