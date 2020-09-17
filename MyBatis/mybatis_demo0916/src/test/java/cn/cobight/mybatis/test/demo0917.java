package cn.cobight.mybatis.test;

import cn.cobight.mybatis.dao.EmpDao;
import cn.cobight.mybatis.dao.NewsDao;
import cn.cobight.mybatis.entity.Dept;
import cn.cobight.mybatis.entity.Emp;
import cn.cobight.mybatis.entity.News;
import cn.cobight.mybatis.entity.NewsType;
import cn.cobight.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * fileName:demo0917
 * description:使用关联查询association,查询新闻及分类表，显示新闻名称，添加时间及所属分类名称
 * author:cobight
 * createTime:2020/9/17 8:28
 * version:1.0.0
 */
public class demo0917 {
    @Test
    public void demo0917test(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            NewsDao newsDao = sqlSession.getMapper(NewsDao.class);
            List<News> newsList = newsDao.listNews();
            if (newsList!=null && newsList.size()>0){
                for (News news : newsList) {
                    System.out.println(news.getTitle()+"\t"+news.getAddTime()+"\t");
                    NewsType newsType = news.getNewsType();
                    if (newsType!=null){
                        System.out.println("--------------"+newsType.gettName());
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
