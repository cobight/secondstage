package com.aaa.ioc.controller;

import com.aaa.ioc.entity.User;
import com.aaa.ioc.service.UserService;
import com.aaa.ioc.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * fileName:UserController
 * description:
 * author:zz
 * createTime:2020/9/22 10:34
 * version:1.0.0
 */
@Controller("userC")  //相当于前两个项目中中的bean
@Scope("prototype")  //配置原型模式，非单例 不写 或者singleton 单例
public class UserController {


    @Autowired
    @Qualifier("userServiceImplment")
    private UserService userService;

    @Value("9527") //普通属性注入
    private  Integer userId;

    /**
     * 获取用户
     */
    public void  getUser(){
        User user = userService.getById(userId);
        if(user!=null){
            System.out.println(user);
        }else {
            System.out.println("用户不存在");
        }
    }

}
