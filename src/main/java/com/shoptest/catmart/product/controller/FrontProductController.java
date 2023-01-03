package com.shoptest.catmart.product.controller;

import com.shoptest.catmart.product.dto.FrontProductDto;
import com.shoptest.catmart.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class FrontProductController {

  private final ProductService productService;

  @GetMapping("/list")
  public String frontProductList(Model model, FrontProductDto parameter) {

    model.addAttribute("frontProductList", productService.selectFrontProductList(parameter));

    return "/product/front_product_list";
  }


}
