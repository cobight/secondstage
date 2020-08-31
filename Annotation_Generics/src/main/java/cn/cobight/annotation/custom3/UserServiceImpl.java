package cn.cobight.annotation.custom3;

import cn.cobight.annotation.custom2.UserDao;


/**
 * fileName:UserServiceImpl
 * description:
 * author:zz
 * createTime:2020/8/27 10:41
 * version:1.0.0
 */
public class UserServiceImpl {

    @Autowired(name = "udao")
    //@Select
    private UserDao userDao;
}
