package cn.cobight.ssm.dao;

import cn.cobight.ssm.entity.NewsFile;

import java.util.List;

/**
 * fileName:NewsFileDao
 * description:
 * author:cobight
 * createTime:2020/9/26 9:13
 * version:1.0.0
 */
public interface NewsFileDao {
    List<NewsFile> listByNewsId(int newsId);
    int add(NewsFile newsFile);
    int del(int newsId);
    int delById(int id);
}
