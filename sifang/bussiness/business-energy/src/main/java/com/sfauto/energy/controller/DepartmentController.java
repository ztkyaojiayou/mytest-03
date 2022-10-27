package com.sfauto.energy.controller;

import com.sfauto.energy.dao.DepartmentMapper;
import com.sfauto.energy.pojo.Department;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author :zoutongkun
 * @date :2022/10/25 10:55 上午
 * @description :
 * @modyified By:
 */
@RestController
public class DepartmentController {

  @Autowired
  DepartmentMapper departmentMapper;

  /**
   * 查询所有部门
   *
   * @return
   */
  @GetMapping("/getDepartments")
  public List<Department> getDepartments() {
    List<Department> departments = departmentMapper.getDepartments();
    if (CollectionUtils.isEmpty(departments)) {
      // 当没有全局处理异常时，则该异常只会在控制台打印出来，而不会返回给前端页面，
      // 此时前端页面只会展示：Whitelabel Error Page--> There was an unexpected error (type=Internal Server
      // Error, status=500).
      throw new RuntimeException("无部门数据");
    }
    return departments;
  }

  /**
   * 根据id查询部门
   *
   * @param id
   * @return
   */
  @GetMapping("/getDepartmentById/{id}")
  public Department getDepartmentById(@PathVariable("id") Integer id) {
    Department res = departmentMapper.getDepartmentById(id);
    if (Objects.isNull(res)) {
      // 当没有全局处理异常时，则该异常只会在控制台打印出来，而不会返回给前端页面，
      // 此时前端页面只会展示：Whitelabel Error Page--> There was an unexpected error (type=Internal Server
      // Error, status=500).
      throw new RuntimeException("找不到该部门");
    }
    return res;
  }
}
