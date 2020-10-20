package com.aaa.aop.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * fileName:LogHandler
 * description: 增强类 或者通知类
 * author:zz
 * createTime:2020/9/23 10:31
 * version:1.0.0
 */
@Component
@Aspect
public class LogHandler {

    /**
     * 配置通用切入点
     */
    @Pointcut("execution(public int com.aaa.aop.service.*.*(..))")
    private  void pointA(){
    }

    /**
     * 通用日志记录功能
     */
    //@AfterReturning(pointcut="execution(public int com.aaa.aop.service.*.*(..))")
    //@AfterReturning("pointA()")
    public void saveLog(JoinPoint joinPoint){
        //拿到链接点方法名称
        String methodName = joinPoint.getSignature().getName();
        //获取目标对象名称
        String className = joinPoint.getTarget().getClass().getName();
        //执行方法的参数
        Object[] args = joinPoint.getArgs();
         String  str = "";
        if(args!=null&&args.length>0){
           str= "-------------操作对象"+args[0].getClass().getName();
        }
        System.out.println("-------------模拟日志记录功能2222222222，在操作"+className+"."+methodName+"方法后，记录日志"+str);
    }

    /**
     * 前置通知处理方法。。。
     * @param joinPoint
     */
    //@Before("execution(public int com.aaa.aop.service.*.*(..))")
    //@Before("pointA()")
    public void beforeExecute(JoinPoint joinPoint){
        System.out.println("---------------在执行业务之前执行");
    }


    /**
     * 环绕通知
     * @param proceedingJoinPoint
     * @return
     */
   // @Around("pointA()")
    public Object aroundExecute(ProceedingJoinPoint proceedingJoinPoint){
       /* proceedingJoinPoint.getSignature().getName();
        proceedingJoinPoint.getTarget().getClass().getName();*/
       Object obj = null;
        System.out.println("-------------调用业务方法之前做什么什么操作。。。。。。。。。。。");
        try {
            obj =  proceedingJoinPoint.proceed();  //调用业务方法
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
       // System.out.println("--------返回值："+obj);
        System.out.println("------------调用业务方法之后做什么什么操作。。。。。。。。。。。返回值："+obj);
       return obj;
    }

    /**
     * 当出现异常时，执行该方法
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "pointA()",throwing = "ex")
    public void exceptionExecute(JoinPoint joinPoint,Exception ex){
        String name = joinPoint.getSignature().getName();
        System.out.println("在执行"+name+"方法时，出现"+ex.getClass().getName()+
                "的异常，具体描述:"+ex.getMessage());
    }

    /**
     * 无论有没有异常都会执行方法处理收尾业务
     * @param joinPoint
     */
    @After("pointA()")
    public void afterExecute(JoinPoint joinPoint){
        System.out.println("无论有没有异常，我都会执行。。。。。。。。。");
    }
}
