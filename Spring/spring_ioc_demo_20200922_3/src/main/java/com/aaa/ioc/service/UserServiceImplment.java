package com.aaa.ioc.service;

import com.aaa.ioc.dao.UserDao;
import com.aaa.ioc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * fileName:UserServiceImplment
 * description:
 * author:cobight
 * createTime:2020/9/23 14:46
 * version:1.0.0
 */
@Service
public class UserServiceImplment implements UserService{
    @Autowired //依赖注入  使用注解方式  代替 property   construtor-arg...
    private UserDao userDao;
    @Override
    public User getById(int userId) {
        System.out.println("implement");
        return userDao.getById(userId);
    }
}
