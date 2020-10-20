package com.aaa.ioc.dao;

import com.aaa.ioc.entity.User;

/**
 * fileName:UserDaoImpl
 * description:
 * author:cobight
 * createTime:2020/9/21 19:13
 * version:1.0.0
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User getById(int userId) {
        if (userId==9527) return new User("userName","realName",22);
        return null;
    }
}
