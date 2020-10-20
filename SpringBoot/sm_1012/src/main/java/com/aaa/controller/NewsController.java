package com.aaa.controller;

import com.aaa.entity.News;
import com.aaa.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * fileName:NewsController
 * description:
 * author:cobight
 * createTime:2020/10/12 19:03
 * version:1.0.0
 */
@RestController
@RequestMapping("news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("list")
    public List<News> list(){
        return newsService.list();
    }
}
