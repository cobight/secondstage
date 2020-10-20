package com.aaa.ssm.dao;

import com.aaa.ssm.entity.News;

import java.util.List;
import java.util.Map;

/**
 * fileName:NewsDao
 * description:
 * author:zz
 * createTime:2020/9/26 9:06
 * version:1.0.0
 */
public interface NewsDao {

    /**
     * 新闻列表
     * @return
     */
     List<News> list();

    /**
     * 新闻添加
     * @param news
     * @return
     */
     int add(News news);

    /**
     * 查询所有的新闻类型
     * @return
     */
    List<Map>  listAllNewsType();
}
