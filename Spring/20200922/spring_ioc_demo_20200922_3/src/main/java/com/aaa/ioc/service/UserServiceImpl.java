package com.aaa.ioc.service;

import com.aaa.ioc.dao.UserDao;
import com.aaa.ioc.dao.UserDaoImpl;
import com.aaa.ioc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * fileName:UserServiceImpl
 * description:
 * author:zz
 * createTime:2020/9/22 10:33
 * version:1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired //依赖注入  使用注解方式  代替 property   construtor-arg...
    private UserDao userDao;
    @Override
    public User getById(int userId) {
        return userDao.getById(userId);
    }

}
