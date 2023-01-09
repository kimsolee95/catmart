package com.shoptest.catmart.cart.mapper;

import com.shoptest.catmart.cart.dto.CartItemDetailDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {

  /* 고객용 - 장바구니 내역 목록 조회 */
  List<CartItemDetailDto> selectCartItemDetailList(Long memberId);


}
