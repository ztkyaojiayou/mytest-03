package com.tkzou.dao;

import com.tkzou.pojo.User;

import java.util.List;

/**
 * 一般也会写成UserMapper，二者表达的是一个意思
 *
 * @author zoutongkun
 */
public interface UserDao {
  /**
   * 查询所有用户
   * @return
   */
  List<User> getUserList();
}
