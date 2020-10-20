package cn.cobight.mybatis.entity;

import java.util.Date;

/**
 * fileName:News
 * description:
 * author:cobight
 * createTime:2020/9/17 16:13
 * version:1.0.0
 */
public class News {
    private Integer newsId;
    private String title;
    private Date addTime;
    private NewsType newsType;

    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }

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
