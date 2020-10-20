package com.aaa.sbm.dao;

import com.aaa.sbm.entity.Dept;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * fileName:DeptDao
 * description:
 * author:zz
 * createTime:2020/10/12 11:52
 * version:1.0.0
 */
public interface DeptDao {

    /**
     * 部门列表
     * @return
     */
    @Select("select deptno,dname,loc from dept")  //建议使用mapper
    List<Dept>  list();
}
