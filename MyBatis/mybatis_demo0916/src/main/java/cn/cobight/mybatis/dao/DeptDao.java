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
    //select案例入门
    List<Dept> listDept();
    //带参查找
    Dept getDeptById(int deptNo);
    //if语句
    List<Dept> listDeptByParamIf(Dept dept);
    //if + where
    List<Dept> listDeptByParamIfWhere(Dept dept);
    //choose when otherwise选择，类似switch
    List<Dept> listDeptByParamChoose(Dept dept);
    //set用法
    int updateDeptParam(Dept dept);
    //加头与去头
    List<Dept> listDeptByParamTrim(Dept dept);
    //遍历
    List<Dept> listDeptByParamForeach(List list);
}
