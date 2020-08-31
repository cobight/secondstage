package cn.cobight.annotation.custom2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * fileName:Select
 * description:修饰方法
 * author:zz
 * createTime:2020/8/27 10:00
 * version:1.0.0
 */
@Target({ElementType.METHOD})  //指定该注解用于方法上
@Retention(RetentionPolicy.RUNTIME) //指定该注解保留到运行时
public @interface  Select {

    //属性
    String value() default "select * from mysql.user";

}
