package com.aaa.ioc.test;

import com.aaa.ioc.controller.UserController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * fileName:IocTest
 * description:
 * author:zz
 * createTime:2020/9/22 10:37
 * version:1.0.0
 */
public class IocTest {

    @Test
    public void testGetUser(){
        //实例化对象
       // UserController userController = new UserController();
        //使用spring提供加载配置文件ClassPathXmlApplicationContext 加载配置文件，读配置为资源，解析资源，
        // 生成bean定义，注册到BeanFactory  ，又beanFactory管理bean   初始化上下文对象   多态
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //调用父类接口中beanFactory
        UserController userController =(UserController)applicationContext.getBean("userC");
        UserController userController1 =(UserController)applicationContext.getBean("userC");
        UserController userController2 =(UserController)applicationContext.getBean("userC");
        //所有交给spring管理的对象默认都是单例模式
        System.out.println(userController==userController1);
        System.out.println(userController==userController2);
        System.out.println(userController1==userController2);
        userController.getUser();
        userController1.getUser();
        userController2.getUser();
    }
}
