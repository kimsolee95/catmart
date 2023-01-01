package com.shoptest.catmart.admin.service.impl;

import com.shoptest.catmart.admin.domain.Category;
import com.shoptest.catmart.admin.dto.CategoryDto;
import com.shoptest.catmart.admin.repository.CategoryRepository;
import com.shoptest.catmart.admin.service.CategoryImgService;
import com.shoptest.catmart.admin.service.CategoryService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryImgService categoryImgService;

  @Override
  @Transactional
  public boolean saveCategory(CategoryDto categoryDto, MultipartFile fileInput) {

    Category category = Category.builder()
        .categoryName(categoryDto.getCategoryName())
        .createdAt(LocalDateTime.now())
        .build();
    Category saveCategory = categoryRepository.save(category);

    try {
      categoryImgService.saveCategoryImg(saveCategory, fileInput);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return true;
  }
}
