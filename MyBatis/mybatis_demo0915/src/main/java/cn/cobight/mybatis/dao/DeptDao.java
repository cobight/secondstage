package cn.cobight.mybatis.dao;

import cn.cobight.mybatis.entity.Dept;

import java.util.List;

/**
 * fileName:DeptDao
 * description:
 * author:cobight
 * createTime:2020/9/15 20:26
 * version:1.0.0
 */
public interface DeptDao {
    List<Dept> listDept();
    Dept getDeptById(int deptNo);
}
