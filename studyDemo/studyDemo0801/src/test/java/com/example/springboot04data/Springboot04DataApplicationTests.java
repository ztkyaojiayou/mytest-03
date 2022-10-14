package com.example.springboot04data;

import cn.hutool.poi.excel.cell.CellUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
class Springboot04DataApplicationTests {

  @Test
  void contextLoads() {}

  @Test
  void test() {
    String s = "";
    // 使用阿里的easyExcel工具类
    ExcelReaderBuilder read = EasyExcel.read();
  }
}
