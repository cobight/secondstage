package cn.cobight.ssm.service;

import cn.cobight.ssm.entity.News;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * fileName:NewsService
 * description:
 * author:cobight
 * createTime:2020/9/26 9:36
 * version:1.0.0
 */
public interface NewsService {
    List<News> listNewsAndFiles();
    int add(News news, MultipartFile[] file, HttpServletRequest request) throws IOException;
    List<Map> listAllNewsType();
    int delNews(int newsId);
    int delFile(int id);
    News getNews(int newsId);
    int updateNews(News news);
    void addFiles(News news,MultipartFile[] file,HttpServletRequest request);
}
