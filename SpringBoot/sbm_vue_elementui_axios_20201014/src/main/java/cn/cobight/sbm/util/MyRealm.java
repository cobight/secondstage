package cn.cobight.sbm.util;

import cn.cobight.sbm.entity.User;
import cn.cobight.sbm.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fileName:MyRealm
 * description:
 * author:cobight
 * createTime:2020/10/13 10:50
 * version:1.0.0
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User)principalCollection.getPrimaryPrincipal();
        List<Map> roleMapList = userService.listRoleByUserId(user.getUserId());
        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
        //判断
        if(roleMapList!=null&&roleMapList.size()>0){
            for (Map map : roleMapList) {
                // 副总1 副总3
                simpleAuthenticationInfo.addRole(map.get("ROLE_NAME")+"");
            }
        }
        //simpleAuthenticationInfo
        // simpleAuthenticationInfo.a  该用户拥有的所有权限放入
        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户信息
        String userName = (String) authenticationToken.getPrincipal();
        //调用服务层
        Map map = new HashMap<>();
        map.put("userName",userName);

        List<User> userList = userService.list(map);
        //判断用户是否存在
        if (userList==null||userList.size()==0){
            System.out.println("找不到用户");
            throw new AccountException();
        }
        //获取用户信息
        User user = userList.get(0);
        //获取加密密码
        String passWord = user.getPassWord();
        //获取盐值
        String passWordSalt = user.getPassWordSalt();

        return new SimpleAuthenticationInfo(user,passWord, ByteSource.Util.bytes(passWordSalt),getName());
    }
}
