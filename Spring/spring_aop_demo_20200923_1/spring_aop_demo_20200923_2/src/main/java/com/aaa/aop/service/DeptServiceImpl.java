package com.aaa.aop.service;

import com.aaa.aop.entity.Dept;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * fileName:DeptServiceImpl
 * description:
 * author:zz
 * createTime:2020/9/23 9:54
 * version:1.0.0
 */
@Service
public class DeptServiceImpl implements  DeptService {
    @Override
    public List<Dept> page(Map map) {
        System.out.println("模拟部门分页查询");
        return null;
    }

    @Override
    public Map addDept(Dept dept) {
        System.out.println("模拟部门添加");
        //System.out.println(1/0);
        String str = null;
       // System.out.println(str.length());
        //System.out.println("模拟日志记录功能1");
        return null;
    }

    @Override
    public Map saveDept(Dept dept) {
        System.out.println("模拟部门添加");
        return null;
    }

    @Override
    public int insertDept(Dept dept) {
        System.out.println("模拟部门添加");
        return 0;
    }

    @Override
    public int update(Dept dept) {
        System.out.println("模拟部门更新");
        //System.out.println("模拟日志记录功能1");
        return 0;
    }

    @Override
    public int delete(int deptId) {
        System.out.println("模拟部门删除");
        //System.out.println("模拟日志记录功能1");
        return 0;
    }



}
