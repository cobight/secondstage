package cn.cobight.day2.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * fileName:JDKEmpProxy
 * description:  jdk动态代理
 * author:zz
 * createTime:2020/9/7 11:38
 * version:1.0.0
 */
public class JDKEmpProxy implements InvocationHandler{

    //委托类属性
    private Object obj;

    /**
     * 使用构造给委托类赋值
     * @param obj
     */
    public JDKEmpProxy(Object obj) {
        this.obj = obj;
    }

    /**
     * 获取代理对象
     * @return
     */
    public Object getJdkProxy(){
        //参数1 获取类加载器对象
        //参数2  委托类实现的所有接口
        //参数3  InvocationHandler 接口的实现类
        Class<?> aClass = obj.getClass();
        return Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(),this);
    }

    /**
     * @param proxy 代理类
     * @param method  委托类实现接口中的单个方法
     * @param args=argements  委托类实现接口中的单个方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //执行委托类实现所有接口中的所有方法，按照顺序一个一个执行
        String methodName = method.getName(); //queryAccountMoney，updateMoney
         Object obj = null;
         //判断是否是查询余额方法
        if("queryAccountMoney".equals(methodName)){
            System.out.println("代理类：准备ATM机。。。。。");
            //执行委托类方法
            obj   = method.invoke(this.obj, args);
            System.out.println("代理类：修改ATM机。。。。。");
        }else {
            System.out.println("代理类：大额存取准备工作。。。。");
            //执行委托类方法
            obj   = method.invoke(this.obj, args);
            System.out.println("代理类：善后工作。。。");
        }

        return obj;
    }
}
