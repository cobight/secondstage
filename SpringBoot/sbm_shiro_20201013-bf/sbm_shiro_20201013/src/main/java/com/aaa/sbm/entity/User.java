package com.aaa.sbm.entity;

/**
 * fileName:User
 * description:
 * author:zz
 * createTime:2020/9/29 9:17
 * version:1.0.0
 */
public class User {

   private Integer userId;
   private String userName;
   private String realName;
   private Integer gender;
   private Integer age;
   private String passWord;
   private String passWordSalt;

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWordSalt() {
        return passWordSalt;
    }

    public void setPassWordSalt(String passWordSalt) {
        this.passWordSalt = passWordSalt;
    }
}
