package com.shoptest.catmart.admin.controller;


import com.shoptest.catmart.admin.dto.CategoryDto;
import com.shoptest.catmart.admin.service.CategoryService;
import com.shoptest.catmart.common.model.ResponseResult;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiManagingCategoryController {

  private final CategoryService categoryService;

  @PostMapping("/api/admin/newCategory")
  public ResponseEntity<?> addNewCategory(@RequestPart("data") @Valid CategoryDto categoryDto, @RequestPart MultipartFile fileInput) {

    categoryService.saveCategory(categoryDto, fileInput);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

}
