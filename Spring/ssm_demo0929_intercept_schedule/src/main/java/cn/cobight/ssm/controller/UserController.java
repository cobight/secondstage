package cn.cobight.ssm.controller;

import cn.cobight.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * fileName:UserController
 * description:
 * author:cobight
 * createTime:2020/9/29 9:53
 * version:1.0.0
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;

    @RequestMapping("toLogin")
    public String toLogin(){
        //session.removeAttribute("errorInfo");
        return "login";
    }


    @RequestMapping("userLogin")
    public String userLogin(String userName, String passWord){

        if (userService.userLogin(userName,passWord,session)) {
            return "redirect:dept/list.do";
        }


        session.setAttribute("errorInfo","用户名或者密码错误！");
        return "redirect:toLogin.do";
    }
}
