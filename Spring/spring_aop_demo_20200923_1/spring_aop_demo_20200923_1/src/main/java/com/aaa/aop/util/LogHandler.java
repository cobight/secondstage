package com.aaa.aop.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * fileName:LogHandler
 * description: 增强类 或者通知类
 * author:zz
 * createTime:2020/9/23 10:31
 * version:1.0.0
 */
public class LogHandler {

    /**
     * 通用日志记录功能
     */
    public void saveLog(JoinPoint joinPoint) {
        //拿到链接点方法名称
        String methodName = joinPoint.getSignature().getName();
        //获取目标对象名称
        String className = joinPoint.getTarget().getClass().getName();
        //执行方法的参数
        Object[] args = joinPoint.getArgs();
        StringBuilder str = new StringBuilder("");
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                str.append(arg.getClass().getName());
            }
        }
        System.out.println("切面：AfterReturning后执行->" + className + "." + methodName + "(" + str.toString() + ")");
        //System.out.println("-------------模拟日志记录功能2222222222，在操作"+className+"."+methodName+"方法后，记录日志"+str);
    }

    /**
     * 前置通知处理方法。。。
     *
     * @param joinPoint
     */
    public void beforeExecute(JoinPoint joinPoint) {
        //拿到链接点方法名称
        String methodName = joinPoint.getSignature().getName();
        //获取目标对象名称
        String className = joinPoint.getTarget().getClass().getName();
        //执行方法的参数
        Object[] args = joinPoint.getArgs();
        StringBuilder str = new StringBuilder("");
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                str.append(arg.getClass().getName());
            }
        }
        System.out.println("切面：Before -->" + className + "." + methodName + "(" + str.toString() + ")");
    }


    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint
     * @return
     */
    public Object aroundExecute(ProceedingJoinPoint proceedingJoinPoint) {
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        StringBuilder str = new StringBuilder("");
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                str.append(arg.getClass().getName());
            }
        }
        Object obj = null;
        System.out.println("--切面：Around ----> 业务执行前->" + className + "." + methodName + "(" + str.toString() + ")");
        try {
            obj = proceedingJoinPoint.proceed();  //调用业务方法
        } catch (Throwable throwable) {
            System.out.println("切面：Around --> 异常" + throwable.getClass().getName());
            //throwable.printStackTrace();
        }
        // System.out.println("--------返回值："+obj);
        System.out.println("--切面：Around ----> 业务执行后，返回值：" + obj);
        return obj;
    }

    /**
     * 当出现异常时，执行该方法
     *
     * @param joinPoint
     * @param ex
     */
    public void exceptionExecute(JoinPoint joinPoint, Exception ex) {
        String name = joinPoint.getSignature().getName();
        System.out.println("切面：AfterThrowing --> 在执行" + name + "方法时，出现" + ex.getClass().getName() +
                "的异常，具体描述:" + ex.getMessage());
    }

    /**
     * 无论有没有异常都会执行方法处理收尾业务
     *
     * @param joinPoint
     */
    public void afterExecute(JoinPoint joinPoint) {
        //拿到链接点方法名称
        String methodName = joinPoint.getSignature().getName();
        //获取目标对象名称
        String className = joinPoint.getTarget().getClass().getName();
        //执行方法的参数
        Object[] args = joinPoint.getArgs();
        StringBuilder str = new StringBuilder("");
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                str.append(arg.getClass().getName());
            }
        }
        System.out.println("切面：After 最后执行 --> " + className + "." + methodName + "(" + str.toString() + ")");
        System.out.println("##########################################################");
    }
}
