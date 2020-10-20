package com.aaa.ioc.controller;

import com.aaa.ioc.entity.User;
import com.aaa.ioc.service.UserService;
import com.aaa.ioc.service.UserServiceImpl;

/**
 * fileName:UserController
 * description:
 * author:zz
 * createTime:2020/9/22 10:34
 * version:1.0.0
 */
public class UserController {


    private UserService userService;

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

    public UserController( Integer userId,UserService userService) {
        this.userService = userService;
        this.userId = userId;
    }

    public UserController() {
    }
}
