package cn.cobight.annotation.custom5;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * fileName:Column
 * description:
 * author:zz
 * createTime:2020/8/27 11:17
 * version:1.0.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    //属性  指定数据库对应的列名称
    String name() default "";
}
