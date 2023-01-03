package com.shoptest.catmart.product.mapper;

import com.shoptest.catmart.product.dto.AdminProductMngDetailDto;
import com.shoptest.catmart.product.dto.AdminProductMngDto;
import com.shoptest.catmart.product.dto.FrontProductDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

  /* 관리자 - 상품 목록 조회 */
  List<AdminProductMngDto> adminProductMngList();

  /* 관리자 - 상품 상세 조회 */
  AdminProductMngDetailDto adminProductMngDetail(long productItemId);

  /* front - 상품 목록 조회 */
  List<FrontProductDto> frontProductList(FrontProductDto parameter);

}
