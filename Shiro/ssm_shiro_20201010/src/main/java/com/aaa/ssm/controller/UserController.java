package com.aaa.ssm.controller;

import com.aaa.ssm.entity.User;
import com.aaa.ssm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        //收集用户信息
        AuthenticationToken  token = new UsernamePasswordToken(userName,passWord);
        //获取主题（当前登录用户主体）
        Subject subject = SecurityUtils.getSubject();
        try {
            //提交用户信息
            subject.login(token);
            Session session = subject.getSession();
            //subject.getPrincipal()  获取到是new SimpleAuthenticationIno() 第一个参数
            User user = (User)subject.getPrincipal();
            session.setAttribute("userInfo", user);
            return "redirect:/dept/list.do";
        }catch (AccountException e){
            session.setAttribute("errorInfo","用户名错误！");
            e.printStackTrace();
        }catch (IncorrectCredentialsException e){
            session.setAttribute("errorInfo","密码错误！");
            e.printStackTrace();
        }catch (AuthenticationException e) {
            session.setAttribute("errorInfo","用户名或者密码错误！");
            e.printStackTrace();
        }
        return "redirect:toLogin.do";
    }

    @RequestMapping("toRegister")
    public String toRegister(){
        return "register";
    }
    /*
    * @description: 注册功能
    * @author: cobight
    * @date: 2020/10/10
    * @params:
     	* @param user:
    * @return: java.lang.String
    */
    @RequestMapping("register")
    public String userRegister(User user, Model model){
        try {
            userService.add(user);
            return "redirect:list.do";
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("errorInfo","注册失败");
        return "forward:toRegister.do";
    }

}
