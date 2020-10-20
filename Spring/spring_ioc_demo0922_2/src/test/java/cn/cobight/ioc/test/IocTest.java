package cn.cobight.ioc.test;

import cn.cobight.ioc.demo1.Person;
import cn.cobight.ioc.demo2.Printer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * fileName:IocTest
 * description:
 * author:cobight
 * createTime:2020/9/22 14:48
 * version:1.0.0
 */
public class IocTest {

    @Test
    public void testUser(){
//        UserController uc=new UserController();
//        uc.getUser();
        //使用spring提供加载配置文件ClassPathXmlApplicationContext 加载配置文件，读配置为资源，解析资源，
        //生成bean定义，注册到BeanFactory ，有BeanFactory管理bean  初始化上下文对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person userController =(Person)applicationContext.getBean("userC");
        userController.fight();
    }
    /*使用IOC思想组装打印机
创建墨汁接口com.aaa.ioc.demo2.Ink及实现类ColorLnk,编写void setColor()方法，实现类中打印：使用彩色打印...
创建纸张接口com.aaa.ioc.demo2.Paper及实现类A5Paper,编写void  setPaper()方法，实现类中打印：使用A4纸打印...
创建打印机类com.aaa.ioc.demo2.Printer,编写属性name,为name赋值"打印机1号",注入Lnk和Paper,编写打印void print()方法
当运行print方法时打印：
             打印机1号开始工作。。。
	     使用彩色打印...
             使用A4纸打印...*/
    @Test
    public void testPrinter(){
//        UserController uc=new UserController();
//        uc.getUser();
        //使用spring提供加载配置文件ClassPathXmlApplicationContext 加载配置文件，读配置为资源，解析资源，
        //生成bean定义，注册到BeanFactory ，有BeanFactory管理bean  初始化上下文对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Printer printer =(Printer)applicationContext.getBean("printer");
        printer.print();
    }
}
