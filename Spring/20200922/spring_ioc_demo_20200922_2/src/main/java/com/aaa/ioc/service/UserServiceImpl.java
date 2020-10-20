package com.aaa.ioc.service;

import com.aaa.ioc.dao.UserDao;
import com.aaa.ioc.dao.UserDaoImpl;
import com.aaa.ioc.entity.User;

/**
 * fileName:UserServiceImpl
 * description:
 * author:zz
 * createTime:2020/9/22 10:33
 * version:1.0.0
 */
public class UserServiceImpl implements UserService {

    //引用dao层
    private UserDao userDao;
    @Override
    public User getById(int userId) {
        return userDao.getById(userId);
    }

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
