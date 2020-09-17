package cn.cobight.mybatis.dao;

import cn.cobight.mybatis.entity.Emp;

import java.util.List;

/**
 * fileName:EmpDao
 * description:
 * author:cobight
 * createTime:2020/9/16 22:02
 * version:1.0.0
 */
public interface EmpDao {

    List<Emp> listEmpsAndDept();
}
