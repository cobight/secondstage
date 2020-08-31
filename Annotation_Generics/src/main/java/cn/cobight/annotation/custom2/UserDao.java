package cn.cobight.annotation.custom2;

import java.util.Map;

/**
 * fileName:UserDao
 * description:
 * author:zz
 * createTime:2020/8/27 10:31
 * version:1.0.0
 */
public interface UserDao {

    /**
     * 根据参数查询用户
     * @param map
     * @return
     */
    //@Select("select * from user where 1=1 and userName =#{userName} and passWord=#{passWord}")
    Map  getUserByParam(Map map);
}
