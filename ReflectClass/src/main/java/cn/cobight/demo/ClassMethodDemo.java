package cn.cobight.demo;

import cn.cobight.entity.Student;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName ClassMethodDemo
 * @Description 操作方法
 * @Author cobight
 * @CreateTime 2020/8/25 21:09
 * @Version 1.0
 **/
public class ClassMethodDemo {
    public static void main(String[] args) {
        //获取Class对象
        Class cla = Student.class;
        //获取所有方法
        Method[] declaredMethods = cla.getDeclaredMethods();
        //遍历所有方法对象
        for (Method declaredMethod : declaredMethods) {
            //获取本方法对象的所有参数对象数组
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            //输出方法对象的方法名 与 本方法的参数个数
            System.out.println("方法名称："+declaredMethod.getName()+",方法参数个数"+parameterTypes.length);
            //如果有参数就输出参数类型
            if (parameterTypes.length>0){
                System.out.println("具体的参数类型为：");
                for (Class<?> parameterType : parameterTypes) {
                    System.out.println("-------"+parameterType.getName());
                }
            }
        }
        try {
            //通过获取的Class，获取一个指定方法名与参数类型与数量的方法对象
            Method playGame = cla.getDeclaredMethod("playGame", String.class, String.class);
            //输出方法名 + 参数个数
            System.out.println("方法名称:"+playGame.getName()+",方法参数个数："+playGame.getParameterTypes().length);
            //方法需要一个具体的学生对象，那就新建一个实例
            Object stu1 = cla.newInstance();
            //获取一个指定的类成员变量 + 赋值
            Field stuName = cla.getDeclaredField("stuName");
            stuName.setAccessible(true);
            stuName.set(stu1,"张三");
            //运行在stu1类中的方法playgame，并 赋予参数
            playGame.invoke(stu1,"射击","CF");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
