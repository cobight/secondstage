package com.aaa.mybatis.dao;

import com.aaa.mybatis.entity.Emp;

import java.util.List;
import java.util.Map;

/**
 * fileName:EmpDao
 * description:
 * author:zz
 * createTime:2020/9/16 11:42
 * version:1.0.0
 */
public interface EmpDao {

    /**
     * 列出员工信息及部门信息
     * @return
     */
    List<Emp>  listEmpsAndDept();

    /**
     * 根据部门名称查询员工信息（存储过程调用）
     * @param paramMap
     */
    void execProListEmpsByDeptName(Map paramMap);

    /**
     * 部门删除
     * @param deptNo
     * @return
     */
    int  deleteById(int deptNo);
}
