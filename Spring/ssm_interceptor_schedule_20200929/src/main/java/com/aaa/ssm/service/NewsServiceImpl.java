package com.aaa.ssm.service;

import com.aaa.ssm.dao.NewsDao;
import com.aaa.ssm.dao.NewsFileDao;
import com.aaa.ssm.entity.News;
import com.aaa.ssm.entity.NewsFile;
import com.aaa.ssm.util.FileHandlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * fileName:NewsServiceImpl
 * description:
 * author:zz
 * createTime:2020/9/26 9:39
 * version:1.0.0
 */
@Service
@Transactional
public class NewsServiceImpl implements  NewsService {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private NewsFileDao newsFileDao;

    @Override
    public List<News> listNewsAndFiles() {
        //使用mybatis一对多可以实现  业务层封装
        //查询新闻列表
        List<News> listNews = newsDao.list();
        if(listNews!=null&&listNews.size()>0){
            for (News news : listNews) {
                //根据新闻ID查询该新闻的所有附件，并赋值
                news.setNewsFileList(newsFileDao.listByNewsId(news.getNewsId()));
            }
        }
        return listNews;
    }

    @Override
    public int add(News news, MultipartFile[] files, HttpServletRequest request) throws IOException {
        int r1 = newsDao.add(news);
        boolean resultBoolean = true;
        //获取新闻Id
        Integer newsId = news.getNewsId();
        for (MultipartFile file : files) {
            if(!file.isEmpty()) {//不为空时，上传
                String savePath = FileHandlerUtil.fileUpload(file, request, "files/imgs/news/");
                int r2 = 0;
                if (savePath != null) {
                    NewsFile newsFile = new NewsFile();
                    //保存原始文件名称
                    newsFile.setFileName(file.getOriginalFilename());
                    //保存目录存在路径（相对路径，通过tomcat容器，访问该图片）
                    newsFile.setFilePath(savePath);
                    newsFile.setNewsId(newsId);
                    r2 = newsFileDao.add(newsFile);
                    if(r2==0){
                        resultBoolean =false;
                    }
                }
            }
        }

       //判断上面两个都执行成功，返回1
       if(r1>0&&resultBoolean){
           return 1;
       }
        return 0;
    }


    @Override
    public List<Map> listAllNewsType() {
        return newsDao.listAllNewsType();
    }

    public static void main(String[] args) {
        String originalFilename ="分.布.式.docx";  //substring(开始位置)   substring(开始位置，结束位置)
        //获取原始文件名称后缀     a.b.c.jpg
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        System.out.println(suffixName);

    }
}
