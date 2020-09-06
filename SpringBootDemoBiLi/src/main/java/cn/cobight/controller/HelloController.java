package cn.cobight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * fileName:HelloController
 * description:
 * author:cobight
 * createTime:2020/9/1 11:08
 * version:1.0.0
 */
@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping
    public String hello(){
        return "Hello World!";
    }
}
