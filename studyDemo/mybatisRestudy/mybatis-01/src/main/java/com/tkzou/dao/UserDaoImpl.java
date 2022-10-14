package com.tkzou.dao;

import com.tkzou.pojo.User;

import java.util.List;

/**
 * 按照之前jdbc的逻辑，我们需要实现dao接口，然后去执行sql语句并返回结果，但在mybatis中则不需要这个实现类啦！！！
 * 而是直接使用一个对应的mapper.xml文件来代替，即每一个dao接口只需对应一个mappper.xml文件即可！！！
 * @author :zoutongkun
 * @date :2022/8/10 12:56 下午
 * @description :
 * @modyified By:
 */
//public class UserDaoImpl implements UserDao{
//    @Override
//    public List<User> getUserList() {
//        //按照原来的逻辑，这里应该就是需要去执行sql啦，但是在mybatis中，这里就使用mapper.xml文件来完成
//        //即把sql语句放到mapper.xml文件中，易知实现了业务代码和sql语句的解耦
//        return null;
//    }
//}
