package com.shoptest.catmart.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @RequestMapping("/main")
  public String index(Model model) {

    return "index";
  }




}
