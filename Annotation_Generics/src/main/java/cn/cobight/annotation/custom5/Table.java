package cn.cobight.annotation.custom5;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * fileName:Table
 * description:
 * author:zz
 * createTime:2020/8/27 11:15
 * version:1.0.0
 */
@Target(ElementType.TYPE) //指定该注解用于类或者接口
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    //属性  指定数据库的名称
    String schema() default "mysql";
    //属性   指定表名称
    String tableName() default "user";
}
