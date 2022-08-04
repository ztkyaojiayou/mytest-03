package com.demo.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zoutongkun
 * @description: TODO
 * @date 2022/7/13 1:04 下午
 */
//@RestController
@Controller
public class IndexController {
  @RequestMapping("/index")
  public String index(Model model, ModelMap modelMap){
    return "index";
  }
}
