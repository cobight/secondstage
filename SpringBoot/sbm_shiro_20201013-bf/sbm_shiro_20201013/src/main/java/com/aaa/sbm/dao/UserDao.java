package com.aaa.sbm.dao;

import com.aaa.sbm.entity.User;
import org.apache.ibatis.annotations.Insert;
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
            "gender,age,password_salt passWordSalt from tb_user <where>" +
            "<if test=\"userName!=null and userName!=''\"> and user_name=#{userName}</if>" +
            "<if test=\"passWord!=null and passWord!=''\"> and pass_word=#{passWord}</if>" +
            "</where></script>")
    List<User>  list(Map map);

    /**
     * 人员添加
     * @param user
     * @return
     */
    @Insert("insert into tb_user(user_id,user_name,real_name,pass_word,password_salt) " +
            "values(seq_user_id.nextval,#{userName},#{realName},#{passWord},#{passWordSalt})")
    int  add(User user);

    /**
     * 根据用户ID获取角色列表
     * @param userId
     * @return
     */
    @Select("select * from tb_role where role_id in (select role_id from tb_user_role where user_id=#{userId})")
    List<Map>  listRoleByUserId(int userId);
}
