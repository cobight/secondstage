package com.aaa.ioc.controller;

import com.aaa.ioc.entity.User;
import com.aaa.ioc.service.UserService;
import com.aaa.ioc.service.UserServiceImpl;

/**
 * fileName:UserController
 * description:
 * author:cobight
 * createTime:2020/9/21 19:16
 * version:1.0.0
 */
public class UserController {
    public static void getUser(){
        UserService us = new UserServiceImpl();
        User user = us.getById(9527);
        if (user!=null) System.out.println(user);
    }
}
