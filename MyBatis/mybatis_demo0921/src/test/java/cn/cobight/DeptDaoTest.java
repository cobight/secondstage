package cn.cobight;

import cn.cobight.dao.DeptDao;
import cn.cobight.entity.Dept;
import cn.cobight.util.Page;
import cn.cobight.util.SqlSessionFactoryUtil;
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
            if (sqlSession != null)sqlSession.close();
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
}
