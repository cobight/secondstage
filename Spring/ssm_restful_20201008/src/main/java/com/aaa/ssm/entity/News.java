package com.aaa.ssm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * fileName:News
 * description:
 * author:zz
 * createTime:2020/9/26 9:06
 * version:1.0.0
 */
public class News {

    private Integer  newsId;
    private String title;
    private String content;
    //前端页面的form表单中，必须是yyyy-MM-dd      2020-10-08
    //@DateTimeFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date addTime;
    private Integer typeId;
    private Integer clickNum;

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public List<NewsFile> getNewsFileList() {
        return newsFileList;
    }

    public void setNewsFileList(List<NewsFile> newsFileList) {
        this.newsFileList = newsFileList;
    }
}
