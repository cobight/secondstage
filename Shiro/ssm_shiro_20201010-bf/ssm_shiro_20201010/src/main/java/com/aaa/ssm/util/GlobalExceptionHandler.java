package com.aaa.ssm.util;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * fileName:GlobalExceptionHandler
 * description:
 * author:zz
 * createTime:2020/9/28 11:29
 * version:1.0.0
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        //设置逻辑视图名称
         modelAndView.setViewName("default-error");
        //绑定错误异常对象
         modelAndView.addObject("ex",e);
        return modelAndView;
    }
}
