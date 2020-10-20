package cn.cobight.mybatis.entity;

import java.util.List;

/**
 * fileName:News
 * description:
 * author:cobight
 * createTime:2020/9/17 21:29
 * version:1.0.0
 */
public class News {
    private Integer newsId;
    private String title;
    private NewsType newsType;
    private List<NewsFile> newsFileList;

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

    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }

    public List<NewsFile> getNewsFileList() {
        return newsFileList;
    }

    public void setNewsFileList(List<NewsFile> newsFileList) {
        this.newsFileList = newsFileList;
    }
}
