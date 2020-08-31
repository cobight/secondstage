package cn.cobight.annotation.custom1;

import java.lang.annotation.Annotation;

/**
 * fileName:TestAnnotation
 * description:
 * author:zz
 * createTime:2020/8/27 9:50
 * version:1.0.0
 */
public class TestAnnotation {
    public static void main(String[] args) {
        //获取UserController的class对象
        Class<UserController> userControllerClass = UserController.class;
        //获取该类的所有注解
        Annotation[] declaredAnnotations = userControllerClass.getDeclaredAnnotations();
        for (Annotation declaredAnnotation : declaredAnnotations) {
            System.out.println(declaredAnnotation);
        }
        System.out.println("-----------------------------------------");
        //获取该类的指定注解
        //判断该类上有没有某一个（Controller）注解
        boolean annotationPresent = userControllerClass.isAnnotationPresent(Controller.class);
        if(annotationPresent) {
            Controller declaredAnnotation = userControllerClass.getAnnotation(Controller.class);
            System.out.println(declaredAnnotation);
            System.out.println(declaredAnnotation.value());
        }
    }
}
