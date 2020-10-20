package cn.cobight.ssm.service;

import cn.cobight.ssm.dao.NewsDao;
import cn.cobight.ssm.dao.NewsFileDao;
import cn.cobight.ssm.entity.News;
import cn.cobight.ssm.entity.NewsFile;
import cn.cobight.ssm.util.FileHandlerUtil;
import com.sun.corba.se.spi.orbutil.fsm.FSMImpl;
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
 * author:cobight
 * createTime:2020/9/26 9:39
 * version:1.0.0
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;
    @Autowired
    private NewsFileDao newsFileDao;

    @Override
    public List<News> listNewsAndFiles() {
        List<News> listNews = newsDao.list();
        if (listNews != null && listNews.size() > 0) {
            for (News news : listNews) {
                news.setNewsFileList(newsFileDao.listByNewsId(news.getNewsId()));
            }
        }
        return listNews;
    }

    @Override
    public int add(News news, MultipartFile[] file, HttpServletRequest request) throws IOException {


        int r1 = newsDao.add(news);
        addFiles(news,file,request);
        if (r1 > 0) return 1;
        return 0;
    }

    @Override
    public List<Map> listAllNewsType() {
        return newsDao.listAllNewsType();
    }

    @Override
    public int delNews(int newsId) {
        int delFile = newsFileDao.del(newsId);
        int delNew = newsDao.del(newsId);
        if (delFile > 0 && delNew > 0) return 1;
        return 0;
    }

    @Override
    public int delFile(int id) {
        return newsFileDao.delById(id);
    }

    @Override
    public News getNews(int newsId) {
        News news = newsDao.getNewsByNewsId(newsId);
        news.setNewsFileList(newsFileDao.listByNewsId(newsId));
        return news;
    }

    @Override
    public int updateNews(News news) {
        return newsDao.update(news);
    }

    @Override
    public void addFiles(News news, MultipartFile[] file,HttpServletRequest request) {
        for (MultipartFile f : file) {
            String savePath = FileHandlerUtil.fileUpload(f, request, "files/imgs/news/");
            if (savePath != null) {
                NewsFile newsFile = new NewsFile();
                newsFile.setFileName(f.getOriginalFilename());
                newsFile.setFilePath(savePath);
                newsFile.setNewsId(news.getNewsId());
                newsFileDao.add(newsFile);
            }
        }
    }

}
