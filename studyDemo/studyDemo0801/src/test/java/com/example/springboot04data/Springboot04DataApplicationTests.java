package com.example.springboot04data;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.example.service.KafkaConsumerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaConsumerService.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Springboot04DataApplicationTests {

//  @Test
//  void contextLoads() {}

  @Test
  void test() {
    String s = "";
    // 使用阿里的easyExcel工具类
    ExcelReaderBuilder read = EasyExcel.read();
  }
}
