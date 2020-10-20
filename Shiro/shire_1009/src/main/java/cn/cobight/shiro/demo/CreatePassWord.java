package cn.cobight.shiro.demo;

import org.apache.shiro.crypto.hash.Sha512Hash;

/**
 * fileName:CreatePassWord
 * description:
 * author:cobight
 * createTime:2020/10/9 18:39
 * version:1.0.0
 */
public class CreatePassWord {
    public static void main(String[] args) {
        String passWord = "20201009";
        String salt = "1009";
        Sha512Hash sha512Hash = new Sha512Hash(passWord,salt,9);
        System.out.println("加密后存入数据库的密码："+sha512Hash.toString());
    }
    public static String passWord(String pwd){
        String salt = "1009";
        Sha512Hash sha512Hash = new Sha512Hash(pwd,salt,9);
        return sha512Hash.toString();
    }
}
