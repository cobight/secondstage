package cn.cobight.singleton.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * fileName:HouseProxy21CenturyJDK
 * description:
 * author:cobight
 * createTime:2020/9/8 15:26
 * version:1.0.0
 */
public class HouseProxy21CenturyJDK implements InvocationHandler {
    //委托类属性
    private Object obj;
    //用构造给属性赋值
    public HouseProxy21CenturyJDK(Object obj) {
        this.obj = obj;
    }
    public <T> T getProxyInstance(){
        Class<?> aClass = obj.getClass();
        return (T)Proxy.newProxyInstance(aClass.getClassLoader(),aClass.getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理：事前大量工作");
        //第一次买，第二次卖
        Object retnV = method.invoke(obj, args);
        System.out.println("代理：事后大量工作");
        return retnV;
    }
}
