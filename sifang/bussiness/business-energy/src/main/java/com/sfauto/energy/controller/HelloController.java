package com.sfauto.energy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :zoutongkun
 * @date :2022/10/25 10:29 上午
 * @description :
 * @modyified By:
 */

@RestController
public class HelloController {

    @RequestMapping(path = {"/hello","/hello2"})
    public String hello(){
        return "Hello World";
    }

}
