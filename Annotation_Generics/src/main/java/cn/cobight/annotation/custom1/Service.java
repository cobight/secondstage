package cn.cobight.annotation.custom1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * fileName:Service
 * description:
 * author:zz
 * createTime:2020/8/27 9:52
 * version:1.0.0
 */
@Target(ElementType.TYPE)  //指定该注解在什么地方被引用
@Retention(RetentionPolicy.RUNTIME) //保留级别  resource  class  runtime
public @interface Service {

    //属性
    String value() default "service";
    String fuc() default "fucku";
}
