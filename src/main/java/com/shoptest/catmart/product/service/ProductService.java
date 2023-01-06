package com.shoptest.catmart.product.service;

import com.shoptest.catmart.product.dto.AdminProductMngDetailDto;
import com.shoptest.catmart.product.dto.AdminProductMngDto;
import com.shoptest.catmart.product.dto.FrontProductDetailDto;
import com.shoptest.catmart.product.dto.FrontProductDto;
import com.shoptest.catmart.product.dto.ProductItemDto;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

  /* 관리자 - 상품 등록 */
  boolean saveProduct(ProductItemDto productItemDto, MultipartFile fileInput);

  /* 관리자 - 상품목록 조회 */
  List<AdminProductMngDto> selectAdminProductMngList();

  /* 관리자 - 상품상세 조회 */
  AdminProductMngDetailDto selectAdminProductMngDetail(long productItemId);

  /* front - 상품목록 조회 */
  List<FrontProductDto> selectFrontProductList(FrontProductDto parameter);

  /* front - 상품상세 조회 */
  FrontProductDetailDto selectFrontProductDetail(Long productItemId);

}
