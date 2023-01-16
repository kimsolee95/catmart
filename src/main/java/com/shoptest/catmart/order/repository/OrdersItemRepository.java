package com.shoptest.catmart.order.repository;

import com.shoptest.catmart.order.domain.OrdersItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersItemRepository extends JpaRepository<OrdersItem, Long> {

}
