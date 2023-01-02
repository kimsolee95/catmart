package com.shoptest.catmart.admin.service;

import com.shoptest.catmart.admin.dto.CategoryDto;
import com.shoptest.catmart.admin.dto.CategorySelectionDto;
import com.shoptest.catmart.admin.dto.FrontCategoryDto;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface CategoryService {

  /* 카테고리 등록 - 관리자 */
  boolean  saveCategory(CategoryDto categoryDto, MultipartFile fileInput);

  /* 카테고리 선택 select box code - 관리자 */
  List<CategorySelectionDto> categorySelectionList();

  /* front main page 카테고리 select */
  List<FrontCategoryDto> selectFrontCategoryList();

}
