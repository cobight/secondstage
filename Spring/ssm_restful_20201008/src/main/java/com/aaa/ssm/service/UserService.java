package com.aaa.ssm.service;

import com.aaa.ssm.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * fileName:UserService
 * description:
 * author:zz
 * createTime:2020/9/29 9:33
 * version:1.0.0
 */
public interface UserService {

    /**
     * 用户列表
     * @param map
     * @return
     */
    List<User> list(Map map);

    /**
     * 用户登录
     * @param userName
     * @param passWord
     * @return
     */
    boolean userLogin(String userName, String passWord, HttpSession session);
}
