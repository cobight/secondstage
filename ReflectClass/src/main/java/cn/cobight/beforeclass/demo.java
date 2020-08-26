package cn.cobight.beforeclass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName demo
 * @Description TODO
 * @Author cobight
 * @CreateTime 2020/8/26 8:33
 * @Version 1.0
 **/
public class demo {
    public static void main(String[] args) {
        Class<Cat> c = Cat.class;
        try {
            //拿到指定参数的构造方法
            Constructor<Cat> declaredConstructor = c.getDeclaredConstructor(String.class);
            //通过构造方法new 实例
            Cat cat = declaredConstructor.newInstance("小智");
            //获取指定成员变量对象
            Field age = c.getDeclaredField("age");
            //赋值
            age.setAccessible(true);
            age.set(cat,5);
            //获取指定方法对象
            Method doSomeThing = c.getDeclaredMethod("doSomeThing",String.class);
            //让指定对象 带参执行
            doSomeThing.invoke(cat,"eatting");

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
class Cat{
    private String name;
    private Integer age;

    public Cat(){}
    public Cat(String name){
        this.name=name;
    }
    /**
     *做什么事情
     */
    public void doSomeThing(String someThing){
        System.out.println("我叫"+name+"今年"+age+"岁,在"+someThing);
    }
}
