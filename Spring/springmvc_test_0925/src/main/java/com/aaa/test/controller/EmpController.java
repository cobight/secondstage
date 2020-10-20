package com.aaa.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fileName:EmpController
 * description:
 * author:cobight
 * createTime:2020/9/25 8:30
 * version:1.0.0
 */
@Controller
public class EmpController {
    @RequestMapping("/empPage")
    public ModelAndView pageEmp(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Emp/list");
        List<Map> empList = new ArrayList<>();
        Map map1 = new HashMap<>();
        map1.put("ename","卢本伟");
        map1.put("sal",666);
        Map map2 = new HashMap();
        map2.put("ename","卢仙");
        map2.put("sal",555);
        empList.add(map1);
        empList.add(map2);
        modelAndView.addObject("empList",empList);
        return modelAndView;
    }
}
