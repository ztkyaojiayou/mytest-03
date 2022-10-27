package com.sfauto.energy.dao;


import com.sfauto.energy.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zoutongkun
 */
@Repository
@Mapper
public interface DepartmentMapper {
    /**
     * 获取所有部门信息
     * @return
     */
    List<Department> getDepartments();

    /**
     *根据部门id获取部门信息
     * @return
     */
    Department getDepartmentById(Integer id);
}
