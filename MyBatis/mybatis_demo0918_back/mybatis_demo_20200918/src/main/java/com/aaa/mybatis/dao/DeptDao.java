package com.aaa.mybatis.dao;

import com.aaa.mybatis.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * fileName:DeptDao
 * description:
 * author:zz
 * createTime:2020/9/15 11:14
 * version:1.0.0
 */
public interface DeptDao {

    /**
     * 部门列表
     * @return
     */
   List<Dept> listDept();
    /**
     * 部门列表
     * @return
     */
    List<Dept> listDeptA();

    /**
     * 部门列表
     * @return
     */
    List<Dept> listDeptB();

    /**
     * 部门更新（set+if根据参数动态拼接更新语句）
     * @param dept
     * @return
     */
    int  updateDeptByParam(Dept dept);

    /**
     * 部门删除
     * @param deptNo
     * @return
     */
    int  deleteById(int deptNo);
   /* int  deleteById(@Param("dn") int deptNo,@Param("de") String dname);*/

    /**
     * 部门添加
     * @param dept
     * @return
     */
   int add(Dept dept);

}
