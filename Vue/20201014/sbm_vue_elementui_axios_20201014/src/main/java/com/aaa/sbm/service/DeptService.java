package com.aaa.sbm.service;

import com.aaa.sbm.entity.Dept;
import com.aaa.sbm.util.Result;

import java.util.List;
import java.util.Map;

/**
 * fileName:DeptService
 * description:
 * author:zz
 * createTime:2020/10/12 11:54
 * version:1.0.0
 */
public interface DeptService {
    /**
     * 部门列表
     * @return
     */
    List<Dept> list();

    /**
     * 部门分页列表
     * @return
     */
    Result page(Map paramMap);


    /**
     * 根据编号查询
     * @param deptNo
     * @return
     */
    Result  getById(int deptNo);

    /**
     * 添加
     * @param dept
     * @return
     */
    Result add(Dept dept);

    /**
     * 更新
     * @param dept
     * @return
     */
    Result update(Dept dept);

    /**
     * 根据编号删除
     * @param deptNo
     * @return
     */
    Result delete(int deptNo);
}
