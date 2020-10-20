package com.aaa.ioc.dao;

import com.aaa.ioc.entity.User;
import org.springframework.stereotype.Component;

/**
 * fileName:UserDaoImpl
 * description:
 * author:zz
 * createTime:2020/9/22 10:30
 * version:1.0.0
 */
@Component
public class UserDaoImpl implements  UserDao {
    @Override
    public User getById(int userId) {
        if(userId==9527){
            return new User(9527,"xingye","唐伯虎",20);
        }
        return null;
    }
}
