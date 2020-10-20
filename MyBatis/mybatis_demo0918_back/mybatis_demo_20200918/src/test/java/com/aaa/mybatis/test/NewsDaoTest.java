package com.aaa.mybatis.test;

import com.aaa.mybatis.dao.NewsDao;
import com.aaa.mybatis.entity.News;
import com.aaa.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fileName:NewsDaoTest
 * description:
 * author:zz
 * createTime:2020/9/18 11:49
 * version:1.0.0
 */
public class NewsDaoTest {

    /**
     * 测试列表方法
     */
    @Test
    public void testListDept(){
        SqlSession sqlSession = null;
        try {
            //使用工具类获取sqlSession
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用jdk代理（代理接口）生成接口DeptDao代理对象
            NewsDao newsDao = sqlSession.getMapper(NewsDao.class);
            Map paramMap = new HashMap();
          //  paramMap.
            List<News> newsList = newsDao.list(paramMap);
            if(newsList!=null&&newsList.size()>0){
                for (News news : newsList) {
                    System.out.println(news.getTitle()+","+news.getAddTime());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession!=null){
                //官网建议放finally 关闭
                sqlSession.close();
            }
        }

    }
    /**
     * 测试列表方法
     */
    @Test
    public void testGetById(){
        SqlSession sqlSession = null;
        try {
            //使用工具类获取sqlSession
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用jdk代理（代理接口）生成接口DeptDao代理对象
            NewsDao newsDao = sqlSession.getMapper(NewsDao.class);
            News news = newsDao.getById(1);
            if(news!=null){
                System.out.println(news.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession!=null){
                //官网建议放finally 关闭
                sqlSession.close();
            }
        }

    }

    /**
     * 测试列表方法
     */
    @Test
    public void testAdd(){
        SqlSession sqlSession = null;
        try {
            //使用工具类获取sqlSession
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用jdk代理（代理接口）生成接口DeptDao代理对象
            NewsDao newsDao = sqlSession.getMapper(NewsDao.class);
            News news = new News();
            news.setNewsId(10);
            news.setTitle("测试标题");
            news.setAddTime(new Date());
            int r = newsDao.add(news);
            sqlSession.commit();
            if(r>0){
                System.out.println("成功");
            }else {
                System.out.println("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession!=null){
                //官网建议放finally 关闭
                sqlSession.close();
            }
        }

    }

    /**
     * 测试列表方法
     */
    @Test
    public void testUpdate(){
        SqlSession sqlSession = null;
        try {
            //使用工具类获取sqlSession
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用jdk代理（代理接口）生成接口DeptDao代理对象
            NewsDao newsDao = sqlSession.getMapper(NewsDao.class);
            News news = new News();
            news.setNewsId(10);
            news.setTitle("测试标题11");
            news.setAddTime(new Date());
            int r = newsDao.update(news);
            sqlSession.commit();
            if(r>0){
                System.out.println("成功");
            }else {
                System.out.println("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession!=null){
                //官网建议放finally 关闭
                sqlSession.close();
            }
        }

    }

    /**
     * 测试列表方法
     */
    @Test
    public void testDelete(){
        SqlSession sqlSession = null;
        try {
            //使用工具类获取sqlSession
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            //使用jdk代理（代理接口）生成接口DeptDao代理对象
            NewsDao newsDao = sqlSession.getMapper(NewsDao.class);

            int r = newsDao.deleteById(10);
            sqlSession.commit();
            if(r>0){
                System.out.println("成功");
            }else {
                System.out.println("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession!=null){
                //官网建议放finally 关闭
                sqlSession.close();
            }
        }

    }
}
