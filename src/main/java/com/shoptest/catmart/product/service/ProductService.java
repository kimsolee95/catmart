package com.shoptest.catmart.product.service;

import com.shoptest.catmart.product.dto.AdminProductMngDetailDto;
import com.shoptest.catmart.product.dto.AdminProductMngDto;
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

}
