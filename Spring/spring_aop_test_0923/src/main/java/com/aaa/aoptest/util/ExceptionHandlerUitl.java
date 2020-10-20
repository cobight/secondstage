package com.aaa.aoptest.util;

import org.aspectj.lang.JoinPoint;

/**
 * fileName:ExceptionHandlerUitl
 * description:
 * author:cobight
 * createTime:2020/9/24 8:33
 * version:1.0.0
 */
public class ExceptionHandlerUitl {
    public void handlerException(JoinPoint joinPoint, Exception ex){
        String name = joinPoint.getSignature().getName();
        System.out.println("在执行"+name+"方法时，出现"+ex.getClass().getName()+
                "的异常，具体描述:"+ex.getMessage());
    }
}
