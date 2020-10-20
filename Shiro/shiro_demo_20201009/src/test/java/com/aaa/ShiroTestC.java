package com.aaa;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * fileName:ShiroTestC
 * description:
 * author:cobight
 * createTime:2020/10/10 8:46
 * version:1.0.0
 */
public class ShiroTestC {
    public static void main(String[] args) {
        // 加载ini创建factory    shiro_jdbcrealm_mysql.ini
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro_jdbcrealm_oracle.ini");
        // 工厂类获取SecurityManager
        SecurityManager securityManager = factory.getInstance();
        //使他可以访问
        SecurityUtils.setSecurityManager(securityManager);
        //获取当前Subject
        Subject currentUser = SecurityUtils.getSubject();
        //判断是否认证
        if (!currentUser.isAuthenticated()) {
            //收集客户端提交的用户和密码
            AuthenticationToken token =
                    new UsernamePasswordToken("scott", "tiger");
            //new UsernamePasswordToken("smith","tiger");
            //提交收集的数据给SecurityManager  ,做认证处理  this.securityManager.login(this, token);
            //org.apache.shiro.authc.Authenticator  authenticate方法，认证
            try {
                currentUser.login(token);
                System.out.println("认证成功");
                //授权
                //角色
                System.out.println("scott用户是否具有role1角色：" + currentUser.hasRole("role1"));//true
                System.out.println("scott用户是否具有role3角色：" + currentUser.hasRole("role3"));//false
                //权限
                System.out.println("scott用户是否具有dept:select权限：" + currentUser.isPermitted("dept:select"));//true
                System.out.println("scott用户是否具有dept:select权限：" + currentUser.isPermitted("dept:alter"));//false
            } catch (AccountException e) {
                System.out.println("用户名错误");
            } catch (IncorrectCredentialsException e) {
                System.out.println("密码错误");
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
        }
        //当前用户退出
        currentUser.logout();
    }
}
