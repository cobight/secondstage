package cn.cobight.ssm.controller;

import cn.cobight.ssm.entity.News;
import cn.cobight.ssm.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * fileName:NewsController
 * description:
 * author:cobight
 * createTime:2020/9/26 9:54
 * version:1.0.0
 */
@Controller
@RequestMapping("news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequestMapping("list")
    public String list(Model model){
        model.addAttribute("newsList",newsService.listNewsAndFiles());
        return "news/list";
    }
    @RequestMapping("toAdd")
    public String toAdd(Model model){

        model.addAttribute("newsTypeList",newsService.listAllNewsType());
        return "news/add";
    }
    //@RequestParam绑定参数
    @RequestMapping(value = "add",method = RequestMethod.POST)//限制只能post
    public String add(News news, @RequestParam MultipartFile[] pic, HttpServletRequest request){//, HttpServletResponse response
        try {
            newsService.add(news,pic,request);
            return "redirect:list.do";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "forward:toAdd.do";
    }

    @RequestMapping("delete")
    public String delete(Integer newsId){
        newsService.delNews(newsId);
        return "redirect:list.do";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(Integer newsId,Model model){
        model.addAttribute("newsTypeList",newsService.listAllNewsType());
        model.addAttribute("news",newsService.getNews(newsId));
        return "news/update";
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(News news, @RequestParam("pic") MultipartFile[] pics, HttpServletRequest request){
        newsService.updateNews(news);
        newsService.addFiles(news,pics,request);
        return "redirect:list.do";
    }

    @RequestMapping("delFile")
    public String delFile(int newsId,int id){
        newsService.delFile(id);
        return "redirect:toUpdate.do?newsId="+newsId;
    }
}
