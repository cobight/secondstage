package com.aaa.ioc.dao;

import com.aaa.ioc.entity.User;

/**
 * fileName:UserDao
 * description:
 * author:cobight
 * createTime:2020/9/21 19:10
 * version:1.0.0
 */
public interface UserDao {
    User getById(int userId);
}
