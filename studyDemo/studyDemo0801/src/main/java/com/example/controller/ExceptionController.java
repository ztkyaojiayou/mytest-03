package com.example.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * springMVC的异常处理机制--注解方式 主接口为HandlerExceptionResolver
 * SpringMVC提供了自定义的异常处理器：SimpleMappingExceptionResolver
 * （默认则是DefaultHandlerExceptionResolver，也是有springMVC自己实现的）
 * 我们不需要再去实现HandlerExceptionResolver这个接口了，而是直接使用这个由springMVC提供的接口即可
 * 此时有有两种方式来自定义异常处理机制，但都是使用的上面这个simpleXXX接口
 * 1）使用xml配置
 * 2）使用注解（常用）
 *
 * @author :zoutongkun
 * @date :2022/8/5 10:32 上午
 * @description :
 * @modyified By:
 */
// 该注解用于声明该类是一个异常处理类，
// 且是一个@Component，即会被Spring进行管理
@ControllerAdvice
public class ExceptionController {
  // 该注解用于指定要处理的异常
  @ExceptionHandler(value = {ArithmeticException.class, NullPointerException.class})
  public String exceptionHandler(Exception ex, Model model) {
    // 将异常信息塞到model中，这样前端页面就可以获取到了，然后就可以展示啦
    model.addAttribute("ex", ex);
    // 然后跳转到指定页面--error.jsp
    return "error";
  }
}
