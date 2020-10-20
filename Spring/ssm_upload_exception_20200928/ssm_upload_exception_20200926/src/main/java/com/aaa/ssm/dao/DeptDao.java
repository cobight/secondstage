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
     * 部门更新
     * @param dept
     * @return
     */
    int   update(Dept dept);
}
