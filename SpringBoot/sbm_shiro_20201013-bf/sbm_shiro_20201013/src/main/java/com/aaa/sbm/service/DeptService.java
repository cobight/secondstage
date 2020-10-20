package com.aaa.sbm.service;

import com.aaa.sbm.entity.Dept;

import java.util.List;

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
}
