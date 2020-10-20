package com.aaa;

import static org.junit.Assert.assertTrue;

import com.aaa.aoptest.service.GoodService;
import com.aaa.aoptest.service.GoodsServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * 2,编写com.aaa.aoptest.service.GoodService接口及实现类GoodsServiceImpl,
     *     编写 int delete()及实现类，模拟删除功能（打印：模拟商品删除）
     *  3,编写增强工具类com.aaa.aoptest.util.ExceptionHandlerUitl 及handlerException方法
     *     在handlerException中打印：在执行 delete方法时，出现什么错误
     *     （模拟出现数组越界异常）
     *  4,编写测试类com.aaa.aoptest.test.AopTest0924,完成测试功能
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring-config.xml");
        GoodService goodService  = (GoodService)applicationContext.getBean("goodService");
        //GoodService goodService = new GoodsServiceImpl();
        goodService.delete();
    }
}
