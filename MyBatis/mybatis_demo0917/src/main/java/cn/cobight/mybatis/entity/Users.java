package cn.cobight.mybatis.entity;

import java.util.List;

/**
 * fileName:Users
 * description:
 * author:cobight
 * createTime:2020/9/17 20:00
 * version:1.0.0
 */
public class Users {
    private Integer userId;
    private String userName;
    private String realName;
    private Integer age;
    private List<Roles> roles;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}
