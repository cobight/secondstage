package com.aaa.ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

/**
 * fileName:UserLoginInterceptor
 * description:
 * author:zz
 * createTime:2020/9/29 10:44
 * version:1.0.0
 */
public class UserLoginInterceptor implements HandlerInterceptor {
    /**
     * 在业务执行前 执行的方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("进入了拦截器。。。。。。。。。。。。。");
        //获取session用户信息   getSession() 或者getSession(true) 获取session没有创建新的  getSession(false) 获取session没有返回null
        Object userInfo = httpServletRequest.getSession().getAttribute("userInfo");
       // String requestURI = httpServletRequest.getRequestURI();
        //用户信息为null 说明没有登录
        if(userInfo==null){
            //重定向到登录页面
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/"+"toLogin.do");
            return false;//中断程序
        }
        //return true; 程序继续执行 return false; 程序中断
        return true;
    }


    /**
     * 在业务执行中拦截处理的方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("在业务执行中拦截处理的方法..........");
    }

    /**
     * 在业务执行后拦截执行的方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("在业务执行后拦截执行的方法..........");
    }
}
