package cn.cobight.ssm.dao;

import cn.cobight.ssm.entity.Users;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

/**
 * fileName:UserDao
 * description:
 * author:cobight
 * createTime:2020/9/29 9:20
 * version:1.0.0
 */
public interface UserDao {
    /*
    * @description: 用户列表查询
    * @author: cobight
    * @date: 2020/9/29
    * @params:
     	* @param map:
    * @return: java.util.List<cn.cobight.ssm.entity.Users>
    */
    @Select("<script>select user_id userId,user_name userName,real_name realName,gender,age,pass_word passWord " +
            "from tb_user <where>" +
            "<if test=\"userName!=null and userName!=''\">and user_name=#{userName}</if>" +
            "<if test=\"passWord!=null and passWord!=''\">and pass_word=#{passWord}</if>" +
            "</where></script>")
    List<Users> list(Map map);

}









