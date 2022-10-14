package com.tkzou.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :zoutongkun
 * @date :2022/8/10 12:55 下午
 * @description :
 * @modyified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private int id;
  private String name;
  private String pwd;
}
