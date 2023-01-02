package com.shoptest.catmart.admin.controller;

import com.shoptest.catmart.admin.service.CategoryService;
import com.shoptest.catmart.product.dto.AdminProductMngDetailDto;
import com.shoptest.catmart.product.dto.ProductItemDto;
import com.shoptest.catmart.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ManagingProductController {

  private final ProductService productService;
  private final CategoryService categoryService;

  @GetMapping("/add.do")
  public String productAdd(Model model) {

    model.addAttribute("categoryList", categoryService.categorySelectionList());

    return "admin/product/add";
  }

  @PostMapping("/add.do")
  public String productAddSubmit(ProductItemDto productItemDto
      , BindingResult bindingResult
      , Model model
      , @RequestParam("fileInput")
      MultipartFile fileInput
  ) {

    if (bindingResult.hasErrors()) {
      return "admin/product/add";
    }

    productService.saveProduct(productItemDto, fileInput);

    return "redirect:/";
  }

  @GetMapping("/list.do")
  public String productList(Model model) {

    model.addAttribute("productList", productService.selectAdminProductMngList());

    return "admin/product/admin_product_list";
  }

  @GetMapping("/detail.do")
  public String productDetail(Model model
  , AdminProductMngDetailDto parameter) {

    AdminProductMngDetailDto detail = productService.selectAdminProductMngDetail(parameter.getProductItemId());
    model.addAttribute("detail", detail);

    return "admin/product/admin_product_detail";
  }


}
