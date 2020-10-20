package cn.cobight.mybatis.test;

import cn.cobight.mybatis.dao.EmpDao;
import cn.cobight.mybatis.dao.NewsTypeDao;
import cn.cobight.mybatis.entity.Emp;
import cn.cobight.mybatis.entity.News;
import cn.cobight.mybatis.entity.NewsType;
import cn.cobight.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fileName:EmpCallTest
 * description:
 * author:cobight
 * createTime:2020/9/17 21:06
 * version:1.0.0
 */
public class EmpCallTest {
    @Test
    public void EmpCall(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            EmpDao empDao = sqlSession.getMapper(EmpDao.class);
            Map paramMap = new HashMap();
            paramMap.put("deptName","ACCOUNTING");
            paramMap.put("empList",new ArrayList<>());
            empDao.execProListEmpsByDeptName(paramMap);
            List<Emp> empList = (List<Emp>)paramMap.get("empList");
            if (empList!=null&&empList.size()>0){
                for (Emp emp : empList) {
                    System.out.println(emp.getEmpNo()+"\t"+emp.getEmpName()+"\t"+emp.getJob()+"\t"+emp.getSalary());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession!=null)sqlSession.close();
        }
    }
}
