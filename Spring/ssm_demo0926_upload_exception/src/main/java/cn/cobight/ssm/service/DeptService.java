package cn.cobight.ssm.service;

import cn.cobight.ssm.entity.Dept;

import java.util.List;

/**
 * fileName:DeptService
 * description:
 * author:cobight
 * createTime:2020/9/25 11:10
 * version:1.0.0
 */
public interface DeptService {
    List<Dept> list();
    Dept getById(int deptNo);
    int update(Dept dept);
    int insert(Dept dept);
    int delete(int deptNo);
}
