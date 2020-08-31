package cn.cobight.annotation.custom1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * fileName:Controller
 * description: 自定义注解  在spring框架中，标志一个类要交给IOC容器管理
 * author:zz
 * createTime:2020/8/27 9:41
 * version:1.0.0
 */
@Target(ElementType.TYPE)  //指定该注解在什么地方被引用
@Retention(RetentionPolicy.RUNTIME) //保留级别  resource  class  runtime
public @interface Controller {

    //使用的属性   如果加上默认值，使用可以不给value赋值
    String  value() default "baseController";
    //  不加默认值，使用时，必须赋值
    //String  value() ;
}
