package cn.cobight.mybatis.dao;

import cn.cobight.mybatis.entity.NewsType;

import java.util.List;

/**
 * fileName:NewsTypeDao
 * description:
 * author:cobight
 * createTime:2020/9/17 19:42
 * version:1.0.0
 */
public interface NewsTypeDao {
    List<NewsType> listNewsTypeAndNews();
}
