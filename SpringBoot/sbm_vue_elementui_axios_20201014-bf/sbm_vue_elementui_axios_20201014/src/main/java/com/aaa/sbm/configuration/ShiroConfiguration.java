package com.aaa.sbm.configuration;

import com.aaa.sbm.util.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * fileName:ShiroConfiguration
 * description:
 * author:zz
 * createTime:2020/10/13 10:39
 * version:1.0.0
 */
//@Configuration  // 相当于spring-shiro-config.xml
public class ShiroConfiguration {

    /**
     * shiro过滤器工厂实例  配置拦截到所有请求进行处理
     * @return
     */
    @Bean // <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
    public ShiroFilterFactoryBean shiroFilter(){
        //实例化对象
         ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
         //让securityManager生效
         shiroFilterFactoryBean.setSecurityManager(securityManager());
         //设置未认证跳转的登录路径
        shiroFilterFactoryBean.setLoginUrl("/html/login.html");
        //定义放开或者拦截的请求路径
        Map<String, String> urlMap = new LinkedHashMap<>(); //按照添加的先后顺序
        //免过滤地址
        urlMap.put("/css/**","anon");
        urlMap.put("/js/**","anon");
        urlMap.put("/images/**","anon");
        urlMap.put("/html/login.html","anon");
        urlMap.put("/html/register.html","anon");
        urlMap.put("/user/login","anon");
        urlMap.put("/user/register","anon");
        //注销
        urlMap.put("/logout","logout");
        //过滤地址
        urlMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(urlMap);
         return shiroFilterFactoryBean;
    }


    /**
     * 实例化SecurityManager
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        return  securityManager;
    }


    /**
     *实例化自定义Realm类
     * @return
     */
    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm =new MyRealm();
        //设值算法类
        myRealm.setCredentialsMatcher(credentialsMatcher());
        return myRealm;
    }

    /**
     *实例化加密算法类
     * @return
     */
    @Bean
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设值加密算法名称
        hashedCredentialsMatcher.setHashAlgorithmName("SHA-512");
        //设置hash次数
        hashedCredentialsMatcher.setHashIterations(1024);
           return hashedCredentialsMatcher;
    }

}
