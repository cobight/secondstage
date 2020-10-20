package com.aaa.shiro.demo1;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import java.util.Arrays;

/**
 * fileName:ShiroDemo
 * description:
 * author:zz
 * createTime:2020/10/9 10:54
 * version:1.0.0
 */
public class ShiroDemo {
    public static void main(String[] args) {
        //加载ini文件
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        //获取SecurityManager实例
        SecurityManager securityManager = factory.getInstance();
        // 让SecurityManager可访问
        SecurityUtils.setSecurityManager(securityManager);
        //获取Subject对象
        Subject currentUser = SecurityUtils.getSubject();
          //定义用户名
        String userName = "smith";
        // 认证
           //收集用户的用户名和密码
        AuthenticationToken authenticationToken = new UsernamePasswordToken(userName, "tiger");
          //将用户名和密码提交给SecurityManager处理
        try {
            //认证是否成功
            currentUser.login(authenticationToken);
            //获取sessioin，记录信息
            Session session = currentUser.getSession();
            //设置用户信息
            session.setAttribute("userName",userName);
            //打印
            System.out.println("认证（登录）成功");
        }catch (AccountException e){
            System.out.println("用户名错误！");
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误!");
        }catch (AuthenticationException e) {
            System.out.println("用户名或者密码错误！");
            //e.printStackTrace();
        }
        //角色判断
        System.out.println("角色------------------------------");
        System.out.println(currentUser.hasRole("role1"));
        System.out.println(currentUser.hasRole("role2"));
        System.out.println(currentUser.hasAllRoles(Arrays.asList("role1","role2")));
        //权限判断
        System.out.println("权限------------------------------");
        System.out.println(currentUser.isPermitted("user:create"));
        System.out.println(currentUser.isPermitted("user:create1"));
        System.out.println(currentUser.isPermitted("goods:create"));
        System.out.println(currentUser.isPermitted("dept:delete"));
        System.out.println(currentUser.isPermittedAll("user:create","dept:delete"));
        System.out.println(currentUser.isPermittedAll("user:create","dept:delete","goods:create"));
    }
}
