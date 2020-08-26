package cn.cobight.demo;

import cn.cobight.entity.Student;

import java.lang.reflect.Field;

/**
 * @ClassName ClassFieldDemo
 * @Description 操作属性
 * @Author cobight
 * @CreateTime 2020/8/25 19:57
 * @Version 1.0
 **/
public class ClassFieldDemo {
    public static void main(String[] args) {
        //获取Class对象
        Class cla = Student.class;
        //获取所有属性
        //获取指定属性，给指定属性赋值
        Field[] declaredFields = cla.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("属性名称为："+declaredField.getName()+"属性类型："+declaredField.getType());
        }
        System.out.println("-------------------");
        //获取指定属性,给指定属性赋值
        try {
            Field stuNo = cla.getDeclaredField("stuNo");
            System.out.println("属性名称为："+stuNo.getName()+",属性的类型："+stuNo.getType());
            Object obj = cla.newInstance();
            stuNo.setAccessible(true);
            stuNo.set(obj,"stu0005");
            System.out.println(stuNo.get(obj));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}






