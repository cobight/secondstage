package com.aaa.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fileName:DeptController
 * description:
 * author:zz
 * createTime:2020/9/24 10:51
 * version:1.0.0
 */
@Controller
public class DeptController {
    /*@Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //设值逻辑视图名称
        modelAndView.setViewName("dept/list");
        //modelAndView.setViewName("/WEB-INF/jsp/dept/list.jsp");
        //向model域中设置值
        List<Map> mapList = new ArrayList<>();
        Map deptMap1  = new HashMap();
        deptMap1.put("deptNo",110);
        deptMap1.put("dname","开发1部");
        deptMap1.put("loc","1楼");
        Map deptMap2  = new HashMap();
        deptMap2.put("deptNo",10);
        deptMap2.put("dname","开发1部");
        deptMap2.put("loc","1楼");
        mapList.add(deptMap1);
        mapList.add(deptMap2);
        modelAndView.addObject("deptList",mapList);
        return modelAndView;
    }*/

    /**
     * 部门列表
     * @return
     */
    @RequestMapping("/deptList")  //请求路径映射配置，相当于bean  name='/'
    public ModelAndView  list(){
        ModelAndView modelAndView = new ModelAndView();
        //设值逻辑视图名称
        modelAndView.setViewName("dept/list");
        //modelAndView.setViewName("/WEB-INF/jsp/dept/list.jsp");
        //向model域中设置值
        List<Map> mapList = new ArrayList<>();
        Map deptMap1  = new HashMap();
        deptMap1.put("deptNo",110);
        deptMap1.put("dname","开发1部");
        deptMap1.put("loc","1楼");
        Map deptMap2  = new HashMap();
        deptMap2.put("deptNo",10);
        deptMap2.put("dname","开发1部");
        deptMap2.put("loc","1楼");
        mapList.add(deptMap1);
        mapList.add(deptMap2);
        modelAndView.addObject("deptList",mapList);
        return modelAndView;
    }
}
