package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 代替springMVC中xml的配置类--使用@Configuration+@EnableWebMvc+@ComponentScan注解 @ComponentScan
 * 的作用就是根据定义的扫描路径，把符合扫描规则的类装配到spring容器中， 也即:所有的注解，只有先被spring扫描到才可能生效，这是使用注解的大前提！！！ 具体而言：
 * value：指定需要扫描的包 @ComponentScan({ “xxx.xxx.xxx.controller”, “xxx.xxx.xxx.service”})
 * basePackages：作用同value；value和basePackages不能同时存在设置，二选一 @ComponentScan(basePackages =
 * {“xxx.xxx.xxx”})
 * basePackageClasses：指定一些类，spring容器会扫描这些类所在的包及其子包中的类 @ComponentScan(basePackageClasses = xxx.class)
 * nameGenerator：自定义bean名称生成器
 *
 * <p>通过value、backPackages、basePackageClasses这3个参数来控制扫描哪些包
 *
 * <p>通过useDefaultFilters、includeFilters、excludeFilters这3个参数来控制过滤器
 *
 * <p>默认情况下，任何参数都不设置的情况下，会将@ComponentScan修饰的类（一般是程序启动类）所在的包作为扫描包；
 * 默认情况下useDefaultFilters为true，这个为true的时候，spring容器内部会使用默认过滤器，
 * 即类上有@Repository、@Service、@Controller、@Component这几个注解中的任何一个， 这个类就会被作为bean注册到spring容器中。
 *
 * @author :zoutongkun
 * @date :2022/8/5 12:07 下午
 * @description :
 * @modyified By:
 */
@Configuration
@ComponentScan("com.example.controller")
// 注意：该注解表示全面接管springMVC，也即不使用springBoot默认对springMVC的相关支持
// 也因此这里的异常处理器都是springMVC自己的，而不是springboot的！！！
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    WebMvcConfigurer.super.configurePathMatch(configurer);
  }

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    WebMvcConfigurer.super.configureContentNegotiation(configurer);
  }

  @Override
  public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    WebMvcConfigurer.super.configureAsyncSupport(configurer);
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    WebMvcConfigurer.super.addFormatters(registry);
  }

  /**
   * 配置自定义的拦截器
   *
   * @param registry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {}

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    WebMvcConfigurer.super.addResourceHandlers(registry);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    WebMvcConfigurer.super.addCorsMappings(registry);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    WebMvcConfigurer.super.addViewControllers(registry);
  }

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    WebMvcConfigurer.super.configureViewResolvers(registry);
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    WebMvcConfigurer.super.addArgumentResolvers(resolvers);
  }

  @Override
  public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
    WebMvcConfigurer.super.addReturnValueHandlers(handlers);
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    WebMvcConfigurer.super.configureMessageConverters(converters);
  }

  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    WebMvcConfigurer.super.extendMessageConverters(converters);
  }

  /**
   * 配置自定义异常处理器 这里的入参并不是我们常见的参数，而是用于存储结果的，类似于返回值对象
   * SimpleMappingExceptionResolver：这是springMVC的，而不是springboot的！！！
   * 其顶级接口为：HandlerExceptionResolver
   * springboot中的异常处理器是：BasicErrorController，其顶级接口为：ErrorController
   * @param resolvers
   */
  @Override
  public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
    Properties properties = new Properties();
    properties.setProperty("java.lang.ArithmeticException", "error");
    exceptionResolver.setExceptionMappings(properties);
    exceptionResolver.setExceptionAttribute("exception");
    resolvers.add(exceptionResolver);
  }

//  /** 可以理解为写法2：即返回需要的对象，而不是在入参出传入，
//   * 只是SpringMVC没有按照这种方式写而已，也因此这样写是无效的，只是便于理解！！！ */
//  public List<HandlerExceptionResolver> configureHandlerExceptionResolvers() {
//    SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
//    Properties properties = new Properties();
//    properties.setProperty("java.lang.ArithmeticException", "error");
//    exceptionResolver.setExceptionMappings(properties);
//    exceptionResolver.setExceptionAttribute("exception");
//    List<HandlerExceptionResolver> list = new ArrayList<>();
//    list.add(exceptionResolver);
//    return list;
//  }

  @Override
  public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    WebMvcConfigurer.super.extendHandlerExceptionResolvers(resolvers);
  }

  @Override
  public Validator getValidator() {
    return WebMvcConfigurer.super.getValidator();
  }

  @Override
  public MessageCodesResolver getMessageCodesResolver() {
    return WebMvcConfigurer.super.getMessageCodesResolver();
  }
}
