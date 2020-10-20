package com.aaa.mybatis.entity;

import java.util.Date;

/**
 * fileName:News
 * description:
 * author:zz
 * createTime:2020/9/17 9:11
 * version:1.0.0
 */
public class News {

    private Integer newsId;
    private String title;
    private Date  addTime;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
