package cn.cobight.mybatis.dao;

import cn.cobight.mybatis.entity.News;

import java.util.List;

/**
 * fileName:NewsDao
 * description:
 * author:cobight
 * createTime:2020/9/17 8:49
 * version:1.0.0
 */
public interface NewsDao {
    List<News> listNews();
}
