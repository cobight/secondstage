package cn.cobight.demo;

import cn.cobight.entity.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName ClassConstructorDemo
 * @Description TODO
 * @Author cobight
 * @CreateTime 2020/8/25 15:49
 * @Version 1.0
 **/
public class ClassConstructorDemo {
    public static void main(String[] args) {
        //获取Class对象
        Class cla = Student.class;
        //获取该类中所有的构造方法
        Constructor[] declaredConstructors = cla.getDeclaredConstructors();//获取所有的构造方法
        //cla.getConstructors();//获取公共的构造方法
        for (Constructor declaredConstructor : declaredConstructors) {
            Class[] parameterTypes = declaredConstructor.getParameterTypes();
            System.out.println("构造方法名称："+declaredConstructor.getName()
                    +"\t构造的参数个数："+declaredConstructor.getParameterTypes().length+",具体的参数类型：");
            for (Class parameterType : parameterTypes) {
                System.out.println("---------:"+parameterType.getName());
            }
        }
        System.out.println("-------------------------------");
        try {
            Constructor declaredConstructor = cla.getDeclaredConstructor(Integer.class, String.class);
            System.out.println("构造方法名称为："+declaredConstructor.getName());
            System.out.println("参数个数："+declaredConstructor.getParameterTypes().length);
            //使用反射实例化对象，先要获取构造对象
            Student student = (Student)declaredConstructor.newInstance(1001, "stu0001");
            System.out.println("学生编号:"+student.getStuNo ()+",学生ID："+student.getStuId());


            //构造空对象
            Student student2 = (Student)cla.newInstance();
            System.out.println(student2.getClass().getName());
            //构造空对象
            Constructor declaredConstructor1 = cla.getDeclaredConstructor();
            Student student3 = (Student)declaredConstructor1.newInstance();
            System.out.println(student3.getClass().getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
