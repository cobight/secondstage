package com.aaa.ioc.service;

import com.aaa.ioc.entity.User;

/**
 * fileName:UserService
 * description:
 * author:zz
 * createTime:2020/9/22 10:33
 * version:1.0.0
 */
public interface UserService {
    /**
     * 根据ID查询对象
     * @param userId
     * @return
     */
    User getById(int userId);
}
