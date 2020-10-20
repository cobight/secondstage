package com.aaa.shiro.util;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * fileName:MyCustomRealm
 * description:
 * author:zz
 * createTime:2020/10/9 11:30
 * version:1.0.0
 */
public class MyCustomRealm extends AuthorizingRealm {
    /**
     * 重写授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 重写认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
         //模拟从数据库中根据用户名获取用户新
            //获取收集到用户名   Principal  主要的，重要的  userName =scott
        String  userName = (String)authenticationToken.getPrincipal();
          //调用服务层 根据用户查询用户信息
         //User user =  userServer.getUserByUserName(userName);
         // 调用对象属性获取数据库中的值
        //String dbUserName = user.getUserName;  //"admin"
        String dbUserName = "scott";
        // 如果根据用户名获取对象为空，则直接抛出账号异常
        if(!userName.equals(dbUserName)){
            throw new AccountException();
        }
        // 加密密码 从数据库中取出
        String dbPassWord ="5cf729699270e380bfc886aedc3fe8f2acf13751c938ac2e46d281510eedf77242b6cd8e5013d505b072fa63bcaa3b7bcd593fce1e6d5e5a5487a0cee33b5fd2";
        // 加密盐值 从数据库中取出
        String  dbPassWordSalt="123456";
          //  第一个参数 数据库取出用户信息（放入session）,第二个参数 加密后密码  第三个参数  加密后盐值的二进制  第四个参数当前realm的名称
        return new SimpleAuthenticationInfo(dbUserName,dbPassWord,
                ByteSource.Util.bytes(dbPassWordSalt),this.getName());
    }
}
