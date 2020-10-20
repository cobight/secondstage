package cn.cobight.sbm.service;

import cn.cobight.sbm.entity.Dept;

import java.util.List;

/**
 * fileName:DeptService
 * description:
 * author:cobight
 * createTime:2020/10/12 16:58
 * version:1.0.0
 */
public interface DeptService {

    List<Dept> list();

    int add(Dept dept);

    int del(int deptNo);

    int upd(Dept dept);
}
