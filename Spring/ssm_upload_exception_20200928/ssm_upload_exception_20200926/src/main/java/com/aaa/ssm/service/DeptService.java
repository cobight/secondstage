package com.aaa.ssm.service;

import com.aaa.ssm.entity.Dept;

import java.util.List;

/**
 * fileName:DeptService
 * description:
 * author:zz
 * createTime:2020/9/25 11:10
 * version:1.0.0
 */
public interface DeptService {
    /**
     * 部门列表
     * @return
     */
    List<Dept> list();
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
