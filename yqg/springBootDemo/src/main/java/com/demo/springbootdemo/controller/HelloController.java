package com.demo.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zoutongkun
 * @description: TODO
 * @date 2022/7/13 1:04 下午
 */
@RestController
public class HelloController {
  @RequestMapping("/hello")
  public String hello(){
    return "hello,world";
  }
}
