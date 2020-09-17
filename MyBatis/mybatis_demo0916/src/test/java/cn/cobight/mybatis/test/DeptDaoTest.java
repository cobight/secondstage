package cn.cobight.mybatis.test;

import cn.cobight.mybatis.dao.DeptDao;
import cn.cobight.mybatis.entity.Dept;
import cn.cobight.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * fileName:DeptDaoTest
 * description:
 * author:cobight
 * createTime:2020/9/15 20:53
 * version:1.0.0
 */
public class DeptDaoTest {
    @Test
    public void testListDept(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态
            //获取列表
            List<Dept> deptList = deptDao.listDept();
            //判断循环 输出
            if (deptList!=null&&deptList.size()>0){
                for (Dept dept : deptList) {
                    System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }

    @Test
    public void testGetDeptById(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态
            //获取列表
            Dept dept = deptDao.getDeptById(10);
            //判断循环 输出
            if (dept!=null){
                System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }

    @Test
    public void listDeptByParamIf(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态

            Dept dept1 = new Dept();
           dept1.setDeptNo(20);
            dept1.setDeptName("A");
            List<Dept> deptList = deptDao.listDeptByParamIf(dept1);

            //判断循环 输出
            if (deptList!=null&&deptList.size()>0){
                for (Dept dept : deptList) {
                    System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }

    @Test
    public void listDeptByParamIfWhere(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态

            Dept dept1 = new Dept();
            dept1.setDeptNo(20);
            dept1.setDeptName("A");
            List<Dept> deptList = deptDao.listDeptByParamIfWhere(dept1);

            //判断循环 输出
            if (deptList!=null&&deptList.size()>0){
                for (Dept dept : deptList) {
                    System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }
    @Test
    public void listDeptByParamChoose(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态

            Dept dept1 = new Dept();
            dept1.setDeptNo(20);
            dept1.setDeptName("A");
            List<Dept> deptList = deptDao.listDeptByParamChoose(dept1);

            //判断循环 输出
            if (deptList!=null&&deptList.size()>0){
                for (Dept dept : deptList) {
                    System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }

    @Test
    public void updateDeptParam(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态

            Dept dept1 = new Dept();
            dept1.setDeptNo(20);
            dept1.setDeptName("RESEARCH1");
            int result = deptDao.updateDeptParam(dept1);
//          事务提交
            sqlSession.commit();
            //判断循环 输出
            if (result>0){
                System.out.println("执行成功");
            }else {
                System.out.println("执行失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }

    @Test
    public void listDeptByParamTrim(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态

            Dept dept1 = new Dept();
            //dept1.setDeptNo(20);
            dept1.setDeptName("A");
            List<Dept> deptList = deptDao.listDeptByParamTrim(dept1);

            //判断循环 输出
            if (deptList!=null&&deptList.size()>0){
                for (Dept dept : deptList) {
                    System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }

    @Test
    public void listDeptByParamForeach(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用JDK代理（代理接口）生成DeptDao代理对象
            DeptDao deptDao = sqlSession.getMapper(DeptDao.class);//多态

            List paramList = new ArrayList();
            paramList.add(10);
            paramList.add(11);
            paramList.add(20);
            paramList.add(40);
            List<Dept> deptList = deptDao.listDeptByParamForeach(paramList);

            //判断循环 输出
            if (deptList!=null&&deptList.size()>0){
                for (Dept dept : deptList) {
                    System.out.println("部门名称"+dept.getDeptName()+",位置："+dept.getLoc());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }
}
