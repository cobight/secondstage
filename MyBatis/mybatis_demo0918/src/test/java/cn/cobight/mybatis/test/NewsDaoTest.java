package cn.cobight.mybatis.test;

import cn.cobight.mybatis.dao.NewsDao;
import cn.cobight.mybatis.entity.News;
import cn.cobight.mybatis.entity.NewsFile;
import cn.cobight.mybatis.entity.NewsType;
import cn.cobight.mybatis.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * fileName:NewsDaoTest
 * description:
 * author:cobight
 * createTime:2020/9/17 21:46
 * version:1.0.0
 */
public class NewsDaoTest {
    @Test
    public void NewsDaoTest(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.getSqlSession();
            NewsDao newsDao = sqlSession.getMapper(NewsDao.class);
            List<News> newsList = newsDao.listNews();
            if (newsList!=null&&newsList.size()>0){
                for (News news : newsList) {
                    System.out.print("新闻title："+news.getTitle());
                    NewsType newsType = news.getNewsType();
                    System.out.println("新闻分类："+newsType.getTname());
                    List<NewsFile> newsFileList = news.getNewsFileList();
                    if (newsFileList!=null&&newsFileList.size()>0){
                        for (NewsFile newsFile : newsFileList) {
                            System.out.println("----------"+newsFile.getFileName());
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
