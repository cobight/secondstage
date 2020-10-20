package com.aaa.entity;

import lombok.Data;

import java.util.Date;

/**
 * fileName:News
 * description:
 * author:cobight
 * createTime:2020/10/12 18:57
 * version:1.0.0
 */
@Data
public class News {
    /*news_id,title,content,addtime*/
    private Integer newsId;
    private String title;
    private String content;
    private Date addtime;

}
