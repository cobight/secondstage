package com.aaa.ssm.controller;

import com.aaa.ssm.entity.User;
import com.aaa.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * fileName:UserController
 * description:
 * author:zz
 * createTime:2020/9/29 9:51
 * version:1.0.0
 */
@Controller
//@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;
    /**
     * 跳转登录
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(){
        //session.removeAttribute("");
        return "login";
    }
    /**
     * 用户登录
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping("userLogin")
    public String userLogin(String userName, String passWord){
        if(userService.userLogin(userName,passWord,session)){
            ///dept/list.do 把部门列表做首页
                return "redirect:/dept/list.do";
        }
        session.setAttribute("errorInfo","用户名或者密码错误！");
        return "redirect:toLogin.do";
    }

}
