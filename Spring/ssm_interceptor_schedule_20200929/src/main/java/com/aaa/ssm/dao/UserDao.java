package com.aaa.ssm.dao;

import com.aaa.ssm.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * fileName:UserDao
 * description:
 * author:zz
 * createTime:2020/9/29 9:20
 * version:1.0.0
 */
public interface UserDao {

    /**
     * 用户带参列表查询
     * private Integer userId;
     private String userName;
     private String realName;
     private Integer gender;
     private Integer age;
     private String passWord;
     * @param map
     * @return
     */
    @Select("<script>select user_id userId,user_name userName,pass_word passWord,real_name realName," +
            "gender,age from tb_user <where>" +
            "<if test=\"userName!=null and userName!=''\"> and user_name=#{userName}</if>" +
            "<if test=\"passWord!=null and passWord!=''\"> and pass_word=#{passWord}</if>" +
            "</where></script>")
    List<User>  list(Map map);
}
