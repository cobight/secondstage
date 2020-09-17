package cn.cobight.mybatis.test;

import cn.cobight.mybatis.dao.EmpDao;
import cn.cobight.mybatis.entity.Dept;
import cn.cobight.mybatis.entity.Emp;
import cn.cobight.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * fileName:EmpDaoTest
 * description:
 * author:cobight
 * createTime:2020/9/16 22:12
 * version:1.0.0
 */
public class EmpDaoTest {
    @Test
    public void testListEmpsAndDept(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            EmpDao empDao = sqlSession.getMapper(EmpDao.class);
            List<Emp> emps = empDao.listEmpsAndDept();
            if (emps!=null && emps.size()>0){
                for (Emp emp : emps) {
                    System.out.println("员工名称："+emp.getEmpName()+"该员工的部门为：");
                    Dept dept = emp.getDept();
                    if (dept!=null){
                        System.out.println("--------------"+dept.getDeptName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }

    }
}
