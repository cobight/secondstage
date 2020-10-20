package com.aaa.ioc.entity;

/**
 * fileName:User
 * description:
 * author:zz
 * createTime:2020/9/22 10:28
 * version:1.0.0
 */
public class User {

    private Integer userId;
    private String userName;
    private String realName;
    private Integer age;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", age=" + age +
                '}';
    }

    public User(Integer userId, String userName, String realName, Integer age) {
        this.userId = userId;
        this.userName = userName;
        this.realName = realName;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
