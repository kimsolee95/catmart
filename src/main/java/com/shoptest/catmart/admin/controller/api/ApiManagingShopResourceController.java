package com.shoptest.catmart.admin.controller.api;


import com.shoptest.catmart.admin.dto.CategoryDto;
import com.shoptest.catmart.admin.service.CategoryService;
import com.shoptest.catmart.common.model.ResponseResult;
import com.shoptest.catmart.product.dto.ProductItemDto;
import com.shoptest.catmart.product.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
  @ApiResponses({
      @ApiResponse(code=200, message="카테고리 정보 생성 완료"),
      @ApiResponse(code=400, message="비즈니스적 오류 시 예외 처리 실행"),
  })
  @ApiOperation(value="관리자용 카테고리 등록 API"
      , notes = "관리자 - 메인 및 상품 조회 시 조건으로 이용될 새 카테고리를 생성합니다. (data는 @RequestPart로 받기 때문에 swagger 문서 상 모델 명시적으로 조회 x)")
  public ResponseEntity<?> addNewCategory(@RequestPart("data") @Valid CategoryDto categoryDto, @RequestPart MultipartFile fileInput) {

    categoryService.saveCategory(categoryDto, fileInput);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

  @PostMapping("/api/admin/newProduct")
  @ApiResponses({
      @ApiResponse(code=200, message="상품정보 생성 완료"),
      @ApiResponse(code=400, message="비즈니스적 오류 시 예외 처리 실행"),
  })
  @ApiOperation(value="관리자용 상품 등록 API", notes = "관리자 - 마켓에서 보여질 상품 정보를 생성합니다. (data는 @RequestPart로 받기 때문에 swagger 문서 상 모델 명시적으로 조회 x)")
  public ResponseEntity<?> addNewProduct(@RequestPart("data") @Valid ProductItemDto productItemDto, @RequestPart MultipartFile fileInput) {

    productService.saveProduct(productItemDto, fileInput);

    ResponseResult responseResult = new ResponseResult(true);
    return ResponseEntity.ok().body(responseResult);
  }

}
