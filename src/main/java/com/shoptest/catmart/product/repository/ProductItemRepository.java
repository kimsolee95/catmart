package com.shoptest.catmart.product.repository;

import com.shoptest.catmart.product.domain.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

}
