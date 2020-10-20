package cn.cobight.mybatis.test;

import cn.cobight.mybatis.dao.NewsTypeDao;
import cn.cobight.mybatis.entity.News;
import cn.cobight.mybatis.entity.NewsType;
import cn.cobight.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * fileName:NewsTypeDaoTest
 * description:
 * author:cobight
 * createTime:2020/9/17 19:42
 * version:1.0.0
 */
public class NewsTypeDaoTest {
    @Test
    public void newsTypeAndNews(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            NewsTypeDao newsTypeDao = sqlSession.getMapper(NewsTypeDao.class);
            List<NewsType> newsTypes = newsTypeDao.listNewsTypeAndNews();
            if (newsTypes!=null&& newsTypes.size()>0){
                for (NewsType newsType : newsTypes) {
                    System.out.println("分类名："+newsType.getTname());
                    List<News> newsList = newsType.getNewsList();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (newsList!=null && newsList.size()>0){
                        for (News news : newsList) {
                            System.out.println("-------"+news.getTitle()+"\t"+sdf.format(news.getAddTime()));
                        }
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
