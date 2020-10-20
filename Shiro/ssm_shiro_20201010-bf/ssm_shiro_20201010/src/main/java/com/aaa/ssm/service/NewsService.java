package com.aaa.ssm.service;

import com.aaa.ssm.entity.News;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * fileName:NewsService
 * description:
 * author:zz
 * createTime:2020/9/26 9:36
 * version:1.0.0
 */
public interface NewsService {

    /**
     * 新闻列表
     * @return
     */
    List<News> listNewsAndFiles();
    /**
     * 查询所有的新闻类型
     * @return
     */
    List<Map>  listAllNewsType();
    /**
     * 新闻添加
     * @param news
     * @return
     */
    int add(News news, MultipartFile[] file, HttpServletRequest request) throws IOException;
}
