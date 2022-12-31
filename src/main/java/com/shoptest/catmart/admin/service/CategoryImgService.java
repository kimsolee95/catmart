package com.shoptest.catmart.admin.service;

import com.shoptest.catmart.admin.domain.Category;
import com.shoptest.catmart.admin.domain.CategoryImg;
import org.springframework.web.multipart.MultipartFile;

public interface CategoryImgService {


  void saveCategoryImg(Category saveCategory, MultipartFile uploadFile) throws Exception;
}
