package com.shoptest.catmart.main;

import com.shoptest.catmart.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

  private final CategoryService categoryService;

  @RequestMapping("/main")
  public String index(Model model) {

    //pr test
    model.addAttribute("frontCategoryList", categoryService.selectFrontCategoryList());

    return "index";
  }




}
