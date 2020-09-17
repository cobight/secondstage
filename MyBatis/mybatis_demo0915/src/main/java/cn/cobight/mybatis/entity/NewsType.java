package cn.cobight.mybatis.entity;

import java.util.Date;

/**
 * fileName:NewsType
 * description:
 * author:cobight
 * createTime:2020/9/16 8:31
 * version:1.0.0
 */
public class NewsType {
    private String tName;
    private Date addTime;


    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }


}
