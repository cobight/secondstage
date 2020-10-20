package com.aaa.ssm.controller;

import com.aaa.ssm.entity.News;
import com.aaa.ssm.service.NewsService;
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

/**
 * fileName:NewsController
 * description:
 * author:zz
 * createTime:2020/9/26 9:53
 * version:1.0.0
 */
@Controller
@RequestMapping("news") //父路径映射
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * 新闻列表
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String  list(Model model){
        model.addAttribute("newsList", newsService.listNewsAndFiles());
        return "news/list";
    }

    /**
     * 跳转新闻添加
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public String toAdd(Model model){
        //获取所有新闻分类
        model.addAttribute("newsTypeList",newsService.listAllNewsType());
        return "news/add";
    }

    /**
     * 新闻添加
     * RequestMapping  method = RequestMethod.POST  限制该方法只能是post请求
     *       @RequestParam  绑定参数
     * @param news
     * @param pics  map.put("b",1)
     * @param request
     * @return
     */
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(News news, @RequestParam("pic") MultipartFile[] pics, HttpServletRequest request){
       /* request.getParameter("title");
        request.getParameter("content");
        response.getWriter().print("");*/
        try {
            newsService.add(news,pics,request);
            //重定向
            return "redirect:list.do";
        } catch (IOException e) {
            e.printStackTrace();
        }
        //转发
        return "forward:toAdd.do";
    }
}
