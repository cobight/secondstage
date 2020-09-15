package cn.cobight.mybatis.test;

import cn.cobight.mybatis.dao.DeptDao;
import cn.cobight.mybatis.entity.Dept;
import cn.cobight.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

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
}
