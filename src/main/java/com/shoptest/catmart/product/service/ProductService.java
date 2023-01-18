package com.shoptest.catmart.product.service;

import com.shoptest.catmart.order.dto.OrderItemAddInputDto;
import com.shoptest.catmart.order.dto.OrderItemByProductDto;
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

  /* front - 주문서(상품 상세보기 내 바로보기 진입 시 필요 조회 data) */
  OrderItemByProductDto selectOrderItemDetailByProduct(OrderItemAddInputDto orderItemAddInputDto);

}
