package com.aaa.shiro.demo2;

import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.realm.jdbc.JdbcRealm;

/**
 * fileName:CreatePassWordBySalt
 * description:
 * author:zz
 * createTime:2020/10/9 11:56
 * version:1.0.0
 */
public class CreatePassWordBySalt {
    public static void main(String[] args) {
        JdbcRealm jdbcRealm = new JdbcRealm();
        String passWord = "tiger";
        String salt = "123456";
        Sha512Hash  sha512Hash = new Sha512Hash(passWord,salt,10);
        System.out.println("加密后存入数据库的密码："+sha512Hash.toString());
    }
}
