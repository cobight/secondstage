package cn.cobight.shiro.util;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * fileName:Realm
 * description:
 * author:cobight
 * createTime:2020/10/9 18:40
 * version:1.0.0
 */
public class Realm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //模拟从数据库中根据用户名获取用户新
        //获取收集到用户名   Principal  主要的，重要的  userName =scott
        String  userName = (String)authenticationToken.getPrincipal();
        //调用服务层 根据用户查询用户信息
        //User user =  userServer.getUserByUserName(userName);
        // 调用对象属性获取数据库中的值
        //String dbUserName = user.getUserName;  //"admin"
        String dbUserName = "admin";
        // 如果根据用户名获取对象为空，则直接抛出账号异常
        if(!userName.equals(dbUserName)){
            throw new AccountException();
        }
        // 加密密码 从数据库中取出
        String dbPassWord ="4afef6926adcb916681678eb520f91522a712d19d6b166cb6a7276c07b9e192da50d7fbf1563cf7b95e8def306f0b8a317629790f1aaf34c07287c5b77a0b9cf";
        // 加密盐值 从数据库中取出
        String  dbPassWordSalt="1009";
        //  第一个参数 数据库取出用户信息（放入session）,第二个参数 加密后密码  第三个参数  加密后盐值的二进制  第四个参数当前realm的名称
        return new SimpleAuthenticationInfo(dbUserName,dbPassWord,
                ByteSource.Util.bytes(dbPassWordSalt),this.getName());
    }
}
