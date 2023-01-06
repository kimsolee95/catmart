package com.shoptest.catmart.admin.service.impl;

import com.shoptest.catmart.admin.domain.Category;
import com.shoptest.catmart.admin.dto.CategoryDto;
import com.shoptest.catmart.admin.dto.CategorySelectionDto;
import com.shoptest.catmart.admin.dto.FrontCategoryDto;
import com.shoptest.catmart.admin.mapper.CategoryMapper;
import com.shoptest.catmart.admin.repository.CategoryRepository;
import com.shoptest.catmart.admin.service.CategoryImgService;
import com.shoptest.catmart.admin.service.CategoryService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryImgService categoryImgService;
  private final CategoryMapper categoryMapper;

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

  @Override
  public List<CategorySelectionDto> categorySelectionList() {

    return categoryMapper.categorySelectionList();
  }

  @Override
  public List<FrontCategoryDto> selectFrontCategoryList() {
    return categoryMapper.frontCategoryList();
  }

  @Override
  public FrontCategoryDto selectFrontCategoryDetail(long categoryId) {

    Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
    if (optionalCategory.isPresent()) {

      Category category = optionalCategory.get();

      FrontCategoryDto detailDto = new FrontCategoryDto();
      detailDto.setCategoryId(category.getCategoryId());
      detailDto.setCategoryName(category.getCategoryName());
      return detailDto;
    }

    return null;
  }
}
