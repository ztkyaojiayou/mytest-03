package com.tkzou.service;

import com.tkzou.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zoutongkun
 */
@Service
public interface DepartmentService {

    /**
     * 获取所有部门信息
     * @return
     */
    List<Department> getDepartments();

    /**
     *根据部门id获取部门信息
     * @return
     */
    Department getDepartmentById();
}
