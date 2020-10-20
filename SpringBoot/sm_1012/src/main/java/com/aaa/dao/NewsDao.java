package com.aaa.dao;

import com.aaa.entity.News;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * fileName:NewsDao
 * description:
 * author:cobight
 * createTime:2020/10/12 18:58
 * version:1.0.0
 */
public interface NewsDao {

    //@Select("select news_id newsId,title,content,addtime from tb_news")
    List<News> list();
}
