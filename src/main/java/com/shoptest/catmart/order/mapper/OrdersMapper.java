package com.shoptest.catmart.order.mapper;

import com.shoptest.catmart.order.dto.OrdersHistoryDetailDto;
import com.shoptest.catmart.order.dto.OrdersHistoryDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper {

  /* 고객용 - 주문내역 목록 조회 */
  List<OrdersHistoryDto> selectOrdersHistoryList(Long memberId);

  /* 고객용 - 주문내역 내 주문 상세조회 */
  List<OrdersHistoryDetailDto> selectOrdersHistoryDetailList(Long memberId, Long ordersId);

}
