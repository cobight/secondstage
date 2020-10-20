package com.aaa.ioc.service;

import com.aaa.ioc.dao.UserDao;
import com.aaa.ioc.dao.UserDaoImpl;
import com.aaa.ioc.entity.User;

/**
 * fileName:UserServiceImpl
 * description:
 * author:cobight
 * createTime:2020/9/21 19:15
 * version:1.0.0
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getById(int userId) {
        UserDao ud = new UserDaoImpl();
        return ud.getById(userId);
    }
}
