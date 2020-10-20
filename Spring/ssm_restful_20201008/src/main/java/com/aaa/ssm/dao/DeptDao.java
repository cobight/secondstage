package com.aaa.ssm.dao;

import com.aaa.ssm.entity.Dept;

import java.util.List;

/**
 * fileName:DeptDao
 * description:
 * author:zz
 * createTime:2020/9/25 10:55
 * version:1.0.0
 */
public interface DeptDao {

    /**
     * 部门列表
     * @return
     */
    List<Dept>  list() ;

    /**
     * 根据ID查询对象
     * @param deptNo
     * @return
     */
    Dept  getById(int deptNo);

    /**
     * 部门添加
     * @param dept
     * @return
     */
    int  add(Dept dept);

    /**
     * 部门删除
     * @param deptNo
     * @return
     */
    int delete(int deptNo);
    /**
     * 部门更新
     * @param dept
     * @return
     */
    int   update(Dept dept);



    /**
     * 备份表插入数据
     * @param dept
     * @return
     */
    int  addBak(Dept dept);
}
