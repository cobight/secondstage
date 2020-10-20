package cn.cobight.shiro.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * fileName:Shiro
 * description:
 * author:cobight
 * createTime:2020/10/9 18:39
 * version:1.0.0
 */
public class Shiro {
    public static void main(String[] args) {
        //加载ini配置
        Factory<SecurityManager> factory  =
                new IniSecurityManagerFactory("classpath:shiro-custom-realm.ini");
        //创建SecurityManager
        SecurityManager securityManager  = factory.getInstance();
        //让securityManager可访问
        SecurityUtils.setSecurityManager(securityManager);
        //获取Subject
        Subject subject  = SecurityUtils.getSubject();
        //认证
        //收集用户名和密码
        AuthenticationToken authenticationToken  =
                new UsernamePasswordToken("admin","20201009");
        //交给sm处理
        //验证是否成功
        try {
            subject.login(authenticationToken);
            System.out.println("认证成功");
            System.out.println("passWord: "+CreatePassWord.passWord("20201009"));
        }catch (AccountException e){
            System.out.println("用户名错误！");
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误!");
        }catch (AuthenticationException e) {
            System.out.println("用户名和密码");
            e.printStackTrace();
        }
    }
}
