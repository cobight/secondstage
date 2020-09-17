package cn.cobight.mybatis.entity;

import java.util.Date;

/**
 * fileName:News
 * description:
 * author:cobight
 * createTime:2020/9/17 8:30
 * version:1.0.0
 */
public class News {
    private Integer NewsId;
    private String title;
    private String content;
    private Date addTime;
    private NewsType newsType;

    public Integer getNewsId() {
        return NewsId;
    }

    public void setNewsId(Integer newsId) {
        NewsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }
}
