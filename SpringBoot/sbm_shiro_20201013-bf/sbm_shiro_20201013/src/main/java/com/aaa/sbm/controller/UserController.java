package com.aaa.sbm.controller;

import com.aaa.sbm.entity.User;
import com.aaa.sbm.service.UserService;
import com.aaa.sbm.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * fileName:UserController
 * description:
 * author:zz
 * createTime:2020/10/13 11:08
 * version:1.0.0
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册方法
     * @param user
     * @return
     */
    @PostMapping("register")
    public Result register(@RequestBody User user){
        userService.add(user);
        return new Result<>(200,"注册成功");//{code:200,message:"注册成功"}
    }

    /**
     * 登录
     * @param userName
     * @param passWord
     * @return
     */
    @GetMapping("login")
    public Result login(String userName,String passWord){
        //实例化主体对象
        Subject subject = SecurityUtils.getSubject();
        //收集用户信息
        AuthenticationToken token = new UsernamePasswordToken(userName,passWord);
        try {
            //登录 提交收集信息
            subject.login(token);
            //获取session
            Session session = subject.getSession();
            //放入用户信息
            session.setAttribute("userInfo",subject.getPrincipal());
            return new Result<>(200,"登录成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return new Result<>(500,"用户名或者密码错误");
    }
}
