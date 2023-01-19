package com.shoptest.catmart.admin.controller;


import com.shoptest.catmart.admin.dto.CategoryDto;
import com.shoptest.catmart.admin.service.CategoryService;
import com.shoptest.catmart.common.model.ResponseResult;
import com.shoptest.catmart.product.dto.ProductItemDto;
import com.shoptest.catmart.product.service.ProductService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * parameter valid 공통 익셉션 적용 위한 ADMIN 상품 등록 api
 * */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiManagingShopResourceController {

  private final CategoryService categoryService;
  private final ProductService productService;

  @PostMapping("/api/admin/newCategory")
  public ResponseEntity<?> addNewCategory(@RequestPart("data") @Valid CategoryDto categoryDto, @RequestPart MultipartFile fileInput) {

    categoryService.saveCategory(categoryDto, fileInput);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

  @PostMapping("/api/admin/newProduct")
  public ResponseEntity<?> addNewProduct(@RequestPart("data") @Valid ProductItemDto productItemDto, @RequestPart MultipartFile fileInput) {

    productService.saveProduct(productItemDto, fileInput);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

}
