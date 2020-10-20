package cn.cobight.ssm.dao;

import cn.cobight.ssm.entity.News;

import java.util.List;
import java.util.Map;

/**
 * fileName:NewsDao
 * description:
 * author:cobight
 * createTime:2020/9/26 9:06
 * version:1.0.0
 */
public interface NewsDao {
    List<News> list();
    int add(News news);
    List<Map> listAllNewsType();
    int del(int newsId);
    News getNewsByNewsId(int newsId);
    int update(News news);
}
