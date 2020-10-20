package cn.cobight.controller;

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
 * fileName:DeptControl
 * description:
 * author:cobight
 * createTime:2020/9/24 19:25
 * version:1.0.0
 */
@Controller
//public class DeptControl implements Controller {
public class DeptControl{
    //@Override
    //public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
    @RequestMapping("/deptList")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dept/deptList");
        List lists = new ArrayList<>();
        Map map1 = new HashMap<>();
        map1.put("deptNo",1001);
        map1.put("deptName","芦苇");
        map1.put("loc","天堂");
        Map map2 = new HashMap<>();
        map2.put("deptNo",1002);
        map2.put("deptName","芦苇2");
        map2.put("loc","天堂2");
        lists.add(map1);
        lists.add(map2);
        modelAndView.addObject("deptList",lists);
        return modelAndView;
    }
}
