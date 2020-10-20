package cn.cobight.mybatis.entity;

import java.util.Date;
import java.util.List;

/**
 * fileName:NewsType
 * description:
 * author:cobight
 * createTime:2020/9/17 19:34
 * version:1.0.0
 */
public class NewsType {
    private Integer typeId;
    private String tname;
    private Date addTime;

    //多对多或者一对多  在一的一方要写多的一方集合
    private List<News> newsList;

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
