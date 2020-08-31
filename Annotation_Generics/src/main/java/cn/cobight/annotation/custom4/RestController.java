package cn.cobight.annotation.custom4;

import java.lang.annotation.*;

/**
 * fileName:RestController
 * description: 演示Documented 和 Inherited
 * author:zz
 * createTime:2020/8/27 10:49
 * version:1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Documented  //生成文档时，是否把注解也生成到文档中
@Inherited  //可以被子孙类继承
public @interface RestController {

    //属性
    String  value()  default "baseController";
}
