package com.aaa.aop.test;

import com.aaa.aop.entity.Dept;
import com.aaa.aop.entity.Order;
import com.aaa.aop.service.DeptService;
import com.aaa.aop.service.OrderService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * fileName:AopTest
 * description:
 * author:zz
 * createTime:2020/9/23 10:01
 * version:1.0.0
 */
public class AopTest {

    @Test
    public void testAop(){
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-config.xml");
        DeptService deptService  = (DeptService)applicationContext.getBean("deptServiceImpl");
        Dept dept = new Dept();
        deptService.addDept(dept);
        deptService.insertDept(dept);
        deptService.update(dept);
        deptService.delete(0);
        OrderService orderService  = (OrderService)applicationContext.getBean("orderServiceImpl");
       Order order  =new Order();
        orderService.add(order);
        orderService.update(order);
        orderService.delete(0);
    }
}
