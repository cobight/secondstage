package com.aaa.sbm.service;


import com.aaa.sbm.entity.User;

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
    boolean userLogin(String userName, String passWord);

    /**
     * 人员添加
     * @param user
     * @return
     */
   int  add(User user);
    /**
     * 根据用户ID获取角色列表
     * @param userId
     * @return
     */
    List<Map>  listRoleByUserId(int userId);
}
