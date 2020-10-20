package com.aaa.service;

import com.aaa.dao.NewsDao;
import com.aaa.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * fileName:NewsServiceImpl
 * description:
 * author:cobight
 * createTime:2020/10/12 19:02
 * version:1.0.0
 */
@Service
@Transactional  //事务
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public List<News> list() {
        return newsDao.list();
    }
}
