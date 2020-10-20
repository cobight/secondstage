package com.aaa.ssm.dao;

import com.aaa.ssm.entity.NewsFile;

import java.util.List;

/**
 * fileName:NewsFileDao
 * description:
 * author:zz
 * createTime:2020/9/26 9:13
 * version:1.0.0
 */
public interface NewsFileDao {

    /**
     * 根据新闻编号查询该新闻的所有附件
     * @param newsId
     * @return
     */
    List<NewsFile>  listByNewsId(int newsId);

    /**
     * 添加附件
     * @param newsFile
     * @return
     */
    int  add(NewsFile newsFile);
}
