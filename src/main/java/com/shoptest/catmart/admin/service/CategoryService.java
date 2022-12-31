package com.shoptest.catmart.admin.service;

import com.shoptest.catmart.admin.dto.CategoryDto;
import org.springframework.web.multipart.MultipartFile;

public interface CategoryService {


  boolean saveCategory(CategoryDto categoryDto, MultipartFile fileInput);
}
