package cn.cobight.sbm.dao;

import cn.cobight.sbm.entity.Dept;
import cn.cobight.sbm.entity.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * fileName:EmpDao
 * description:
 * author:cobight
 * createTime:2020/10/15 18:45
 * version:1.0.0
 */
public interface EmpDao {


    List<Emp> list();


    List<Emp> page(Map paramMap);


    int  pageCount(Map paramMap);


    Emp getById(int empNo);


    int add(Emp emp);


    int del(int empNo);


    int upd(Emp emp);
}
