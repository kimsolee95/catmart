package com.shoptest.catmart.product.controller;

import com.shoptest.catmart.admin.dto.FrontCategoryDto;
import com.shoptest.catmart.admin.service.CategoryService;
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
  private final CategoryService categoryService;

  @GetMapping("/list")
  public String frontProductList(Model model, FrontProductDto parameter) {

    FrontCategoryDto selectCategory = null;
    if (parameter.getCategoryId() != null) {
      selectCategory = categoryService.selectFrontCategoryDetail(parameter.getCategoryId());
    }

    model.addAttribute("selectCategory", selectCategory);
    model.addAttribute("frontProductList", productService.selectFrontProductList(parameter));
    model.addAttribute("categoryList", categoryService.categorySelectionList());

    return "/product/front_product_list";
  }


}
