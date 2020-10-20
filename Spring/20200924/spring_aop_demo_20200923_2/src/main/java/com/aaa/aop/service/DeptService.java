package com.aaa.aop.service;

import com.aaa.aop.entity.Dept;

import java.util.List;
import java.util.Map;

/**
 * fileName:DeptService
 * description:
 * author:zz
 * createTime:2020/9/23 9:48
 * version:1.0.0
 */
public interface DeptService {

    /**
     * 分页列表
     * @param map
     * @return
     */
    List<Dept>  page(Map map);

    /**
     * 添加
     * @param dept
     * @return
     */
    Map  addDept(Dept dept);

    Map saveDept(Dept dept);

    int insertDept(Dept dept);
    /**
     * 更新
     * @param dept
     * @return
     */
    int update(Dept dept);

    /**
     * 删除
     * @param deptId
     * @return
     */
    int delete(int deptId);
}
