package cn.cobight.mybatis.dao;

import cn.cobight.mybatis.entity.NewsType;

import java.util.List;

/**
 * fileName:NewTypeDao
 * description:
 * author:cobight
 * createTime:2020/9/16 8:32
 * version:1.0.0
 */
public interface NewTypeDao {
    List<NewsType> listNewsType();
}
