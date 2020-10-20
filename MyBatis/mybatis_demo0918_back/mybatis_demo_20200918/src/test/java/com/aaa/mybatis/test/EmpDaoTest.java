package com.aaa.mybatis.test;

import com.aaa.mybatis.dao.EmpDao;
import com.aaa.mybatis.entity.Dept;
import com.aaa.mybatis.entity.Emp;
import com.aaa.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fileName:EmpDaoTest
 * description:
 * author:zz
 * createTime:2020/9/16 11:53
 * version:1.0.0
 */
public class EmpDaoTest {

    @Test
    public void testListEmpsAndDept(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            EmpDao empDao = sqlSession.getMapper(EmpDao.class);
            List<Emp> empList = empDao.listEmpsAndDept();
            if(empList!=null&&empList.size()>0){
                for (Emp emp : empList) {
                    System.out.println("员工名称"+emp.getEmpName()+"该员工的部门为：");
                    Dept dept = emp.getDept();
                    if(dept!=null){
                        System.out.println("-------------"+dept.getDeptName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession!=null)
              sqlSession.close();
        }

    }

    @Test
    public void testExecProListEmpsByDeptName(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            EmpDao empDao = sqlSession.getMapper(EmpDao.class);
            //定义输入参数
            Map paramMap  = new HashMap();
            //Map 传值时 mapper文件中#{参数名称}必须和map  put的key的名称完全一致
            paramMap.put("deptName","ACCOUNTING");
            // 实例化接受参数的集合
            paramMap.put("empList",new ArrayList<Emp>());
            // 执行完成后，empList会被赋值
            empDao.execProListEmpsByDeptName(paramMap);
            //解析赋值结果
            List<Emp> empList =  (List<Emp>)paramMap.get("empList");
            //判断  遍历
            if(empList!=null&&empList.size()>0){
                for (Emp emp : empList) {
                    System.out.println("名称"+emp.getEmpName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession!=null)
                sqlSession.close();
        }
    }
}
