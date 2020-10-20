package com.aaa.mybatis.test;

import com.aaa.mybatis.dao.DeptDao;
import com.aaa.mybatis.entity.Dept;
import com.aaa.mybatis.util.Page;
import com.aaa.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fileName:DeptDaoTest
 * description:
 * author:zz
 * createTime:2020/9/15 11:43
 * version:1.0.0
 */
public class DeptDaoTest {

    /**
     * 测试分页方法
     */
    @Test
    public void testPageDeptPlugin() {
        SqlSession sqlSession = null;
        try {
            //使用工具类获取sqlSession
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用jdk代理（代理接口）生成接口DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态
            Page page = new Page(3, 4);
            //获取列表
            List<Dept> deptList = deptDao.pageDeptPlugin(page);
            //int total = deptDao.pageDeptCount(paramMap);
            System.out.println("查询总条数为：" + page.getTotal());
            System.out.println("查询总页数为：" + page.getPageCount());
            //判断循环，输出
            if (deptList != null && deptList.size() > 0) {
                for (Dept dept : deptList) {
                    System.out.println("部门名称:" + dept.getDeptName() + ",位置：" + dept.getLoc());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                //官网建议放finally 关闭
                sqlSession.close();
            }
        }

    }

    /**
     * 测试分页方法
     */
    @Test
    public void testPageDept() {
        SqlSession sqlSession = null;
        try {
            //使用工具类获取sqlSession
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用jdk代理（代理接口）生成接口DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态
            Map paramMap = new HashMap();
            int pageNo = 2;
            int pageSize = 2;
            paramMap.put("end", pageNo * pageSize + 1);
            paramMap.put("start", (pageNo - 1) * pageSize);
            paramMap.put("deptN", "A");

            //获取列表
            List<Dept> deptList = deptDao.pageDept(paramMap);
            int total = deptDao.pageDeptCount(paramMap);
            System.out.println("查询总条数为：" + total);
            //判断循环，输出
            if (deptList != null && deptList.size() > 0) {
                for (Dept dept : deptList) {
                    System.out.println("部门名称:" + dept.getDeptName() + ",位置：" + dept.getLoc());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                //官网建议放finally 关闭
                sqlSession.close();
            }
        }

    }

    /**
     * 测试列表方法
     */
    @Test
    public void testListDept() {
        SqlSession sqlSession = null;
        try {
            //使用工具类获取sqlSession
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用jdk代理（代理接口）生成接口DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态
            //获取列表
            List<Dept> deptList = deptDao.listDept();
            //判断循环，输出
            if (deptList != null && deptList.size() > 0) {
                for (Dept dept : deptList) {
                    System.out.println("部门名称:" + dept.getDeptName() + ",位置：" + dept.getLoc());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                //官网建议放finally 关闭
                sqlSession.close();
            }
        }

    }

    @Test
    public void testAdd() {
        SqlSession sqlSession = null;
        try {
            //使用工具类获取sqlSession
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用jdk代理（代理接口）生成接口DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态
            Dept paramDept = new Dept();
            paramDept.setDeptName("测试部门");
            //获取列表
            int result = deptDao.add(paramDept);
            //提交事务
            sqlSession.commit();
            if (result > 0) {
                System.out.println("执行成功");
            } else {
                System.out.println("执行失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                //官网建议放finally 关闭
                sqlSession.close();
            }
        }
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testOneLevelCache() {
        SqlSession sqlSession = null;
        try {
            //使用工具类获取sqlSession
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用jdk代理（代理接口）生成接口DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态
            //获取列表
            List<Dept> deptList = deptDao.listDept();
            System.out.println(deptList);
            //间隔符
            System.out.println("--------------------------------------------------------");
            //使用jdk代理（代理接口）生成接口DeptDao代理对象
            DeptDao deptDao1 = sqlSession.getMapper(DeptDao.class);//多态
            //获取列表
            List<Dept> deptList1 = deptDao1.listDept();
            System.out.println(deptList1);
            //间隔符
            System.out.println("--------------------执行更新------------------------------------");
            DeptDao deptDaoUpdate = sqlSession.getMapper(DeptDao.class);//多态
            Dept paramDept = new Dept();
            paramDept.setDeptNo(20);
            paramDept.setDeptName("RESEARCH3333");
            //paramDept.setLoc("DALLAS");
            //获取列表
            int result = deptDaoUpdate.updateDeptByParam(paramDept);
            //提交事务
            sqlSession.commit();
            if (result > 0) {
                System.out.println("执行成功");
            } else {
                System.out.println("执行失败");
            }
            //间隔符
            System.out.println("--------------------------------------------------------");
            //使用jdk代理（代理接口）生成接口DeptDao代理对象
            DeptDao deptDao2 = sqlSession.getMapper(DeptDao.class);//多态
            //获取列表
            List<Dept> deptList2 = deptDao2.listDept();
            System.out.println(deptList2);
            //间隔符
            System.out.println("---------------------手动清空缓存-----------------------------------");
            //清空缓存
            sqlSession.clearCache();
            //间隔符
            System.out.println("--------------------------------------------------------");
            //使用jdk代理（代理接口）生成接口DeptDao代理对象
            DeptDao deptDao3 = sqlSession.getMapper(DeptDao.class);//多态
            //获取列表
            List<Dept> deptList3 = deptDao3.listDept();
            System.out.println(deptList3);
            //间隔符
            System.out.println("------------------------执行删除--------------------------------");
           /* DeptDao deptDaoDelete = sqlSession.getMapper(DeptDao.class);
            int reslut  = deptDaoDelete.deleteById(52);
            //提交事务
            sqlSession.commit();
            if(result>0){
                System.out.println("执行成功");
            }else{
                System.out.println("执行失败");
            }*/
            System.out.println("--------------------------------------------------------");
            //使用jdk代理（代理接口）生成接口DeptDao代理对象
            DeptDao deptDao4 = sqlSession.getMapper(DeptDao.class);//多态
            //获取列表
            List<Dept> deptList4 = deptDao3.listDept();
            System.out.println(deptList4);
            //间隔符
            System.out.println("--------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                //官网建议放finally 关闭
                sqlSession.close();
            }
        }
    }


    @Test
    public void testUpdateDeptByParam() {
        SqlSession sqlSession = null;
        try {
            //使用工具类获取sqlSession
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用jdk代理（代理接口）生成接口DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态
            Dept paramDept = new Dept();
            paramDept.setDeptNo(20);
            paramDept.setDeptName("RESEARCH3");
            //paramDept.setLoc("DALLAS");
            //获取列表
            int result = deptDao.updateDeptByParam(paramDept);
            //提交事务
            sqlSession.commit();
            if (result > 0) {
                System.out.println("执行成功");
            } else {
                System.out.println("执行失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                //官网建议放finally 关闭
                sqlSession.close();
            }
        }
    }

}
