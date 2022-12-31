package com.shoptest.catmart.admin.repository;

import com.shoptest.catmart.admin.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
