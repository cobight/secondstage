package cn.cobight.demo;

import cn.cobight.entity.Student;

/**
 * @ClassName GetClassDemo
 * @Description TODO
 * @Author cobight
 * @CreateTime 2020/8/25 10:36
 * @Version 1.0
 **/
public class GetClassDemo {
    public static void main(String[] args) {
        //1.通过getClass()方法
        Student student = new Student();
        Class cla1 = student.getClass();
        System.out.println("全称：" + cla1.getName());
        System.out.println("简称：" + cla1.getSimpleName());
        //2.通过类名称获取class对象
        Class cla2 = Student.class;
        System.out.println("全称：" + cla2.getName());
        System.out.println("简称：" + cla2.getSimpleName());
        //3.通过Class的forName静态方法获取类
        try {
            Class cla3 = Class.forName("cn.cobight.entity.Student");
            System.out.println("全称：" + cla3.getName());
            System.out.println("简称：" + cla3.getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //4.通过类加载器ClassLoader中的loadClass方法获取class对象
        ClassLoader classLoader = GetClassDemo.class.getClassLoader();
        try {
            Class cla4 = classLoader.loadClass("cn.cobight.entity.Student");
            System.out.println("全称：" + cla4.getName());
            System.out.println("简称：" + cla4.getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}






















