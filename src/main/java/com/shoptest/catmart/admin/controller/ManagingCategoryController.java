package com.shoptest.catmart.admin.controller;

import com.shoptest.catmart.admin.domain.CategoryImg;
import com.shoptest.catmart.admin.dto.CategoryDto;
import com.shoptest.catmart.admin.dto.CategoryImgDto;
import com.shoptest.catmart.admin.service.CategoryImgService;
import com.shoptest.catmart.admin.service.CategoryService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class ManagingCategoryController {

  private final CategoryService categoryService;

  @GetMapping("/add.do")
  public String categoryAdd(Model model) {

    return "admin/category/add";
  }

}
