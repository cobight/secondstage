package cn.cobight.mybatis.dao;

import cn.cobight.mybatis.entity.Users;

import java.util.List;

/**
 * fileName:UsersAndRolesDao
 * description:
 * author:cobight
 * createTime:2020/9/17 20:03
 * version:1.0.0
 */
public interface UsersAndRolesDao {
    List<Users> listGetUsersAndRoles();
}
