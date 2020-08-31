package cn.cobight.annotation.custom3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * fileName:Autowired
 * description:修饰属性
 * author:zz
 * createTime:2020/8/27 10:38
 * version:1.0.0
 */
@Target({ElementType.FIELD}) //指定该注解引用的地方
@Retention(RetentionPolicy.RUNTIME)//指定该注解保留级别
public @interface Autowired {

    //值属性
    String value() default  "";
    //名称属性
    String name() default  "";
    String name1() default  "1";
    String name2() default  "2";
}
