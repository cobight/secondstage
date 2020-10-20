package com.aaa.service;

import com.aaa.dao.NewsDao;
import com.aaa.entity.News;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * fileName:NewsService
 * description:
 * author:cobight
 * createTime:2020/10/12 19:00
 * version:1.0.0
 */
public interface NewsService {
    List<News> list();

}
