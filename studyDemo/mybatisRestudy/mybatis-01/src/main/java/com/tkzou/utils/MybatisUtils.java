package com.tkzou.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 使用mybatis操作sql的工具类（固定）
 * @author :zoutongkun
 * @date :2022/8/10 12:29 下午
 * @description :
 * @modyified By:
 */
public class MybatisUtils {
  // 生产sqlSession对象的工厂
  private static SqlSessionFactory sqlSessionFactory;

  static {
    String resource = "mybatis-config.xml";
    try {
      // 读取配置文件
      InputStream resourceAsStream = Resources.getResourceAsStream(resource);
      // 获取工厂对象
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  // 然后就可以获取sqlSession对象了，而该对象就是用于操作sql语句的核心对象！！！
  public static SqlSession getSqlSession(){
    return sqlSessionFactory.openSession();
  }
}
