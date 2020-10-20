package cn.cobight.ssm.dao;

import cn.cobight.ssm.entity.Dept;

import java.util.List;

/**
 * fileName:DeptDao
 * description:
 * author:cobight
 * createTime:2020/9/25 10:55
 * version:1.0.0
 */
public interface DeptDao {
    /*部门列表*/
    List<Dept> list();
    Dept getById(int deptNo);
    int update(Dept dept);
    int insert(Dept dept);
    int delete(int deptNo);
}
