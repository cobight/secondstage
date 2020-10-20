package com.aaa.ioc.dao;

import com.aaa.ioc.entity.User;

/**
 * fileName:UserDao
 * description:
 * author:zz
 * createTime:2020/9/22 10:30
 * version:1.0.0
 */
public interface UserDao {

    /**
     * 根据ID查询对象
     * @param userId
     * @return
     */
    User   getById(int userId);
}
