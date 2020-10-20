package com.aaa.ioc.service;

import com.aaa.ioc.entity.User;

/**
 * fileName:UserService
 * description:
 * author:cobight
 * createTime:2020/9/21 19:11
 * version:1.0.0
 */
public interface UserService {
    User getById(int userId);
}
