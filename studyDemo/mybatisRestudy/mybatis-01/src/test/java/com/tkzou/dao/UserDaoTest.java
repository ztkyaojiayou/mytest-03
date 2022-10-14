package com.tkzou.dao;

import com.tkzou.pojo.User;
import com.tkzou.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

/**
 * @author :zoutongkun
 * @date :2022/8/10 1:24 下午
 * @description :
 * @modyified By:
 */
public class UserDaoTest {
  // 得到当前类的信息，用于日志输出（务必掌握）
  static Logger logger = Logger.getLogger(UserDaoTest.class);

  @Test
  public void test() {
    logger.info("info：进入selectUser方法");
    logger.debug("debug：进入selectUser方法");
    logger.error("error: 进入selectUser方法");
    SqlSession sqlSession = MybatisUtils.getSqlSession();
    // 即获取一个mapper/dao接口
    UserDao userDao = sqlSession.getMapper(UserDao.class);
    // 然后就可以直接调用这个接口中的方法啦--mybatis就会自动去找对应的mapper.xml文件执行sql并返回结果集对象
    // 而无需再使用dao/mapper接口的实现类啦
    List<User> userList = userDao.getUserList();
    for (User user : userList) {
      // 输出
      System.out.println(user);
    }
    sqlSession.close();
  }
}
