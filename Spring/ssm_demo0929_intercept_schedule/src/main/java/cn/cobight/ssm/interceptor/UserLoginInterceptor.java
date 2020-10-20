package cn.cobight.ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * fileName:UserLoginInterceptor
 * description:
 * author:cobight
 * createTime:2020/9/29 10:46
 * version:1.0.0
 */
public class UserLoginInterceptor implements HandlerInterceptor {
    @Override//执行前
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("在业务执行前拦截");
        /*如果不写或者为true，session没有时就创建一个新的
        * 如果为false，session不存在就返回null*/
        Object userInfo = httpServletRequest.getSession().getAttribute("userInfo");
        if (userInfo==null){
            //未登录，跳登录页面
            //重定向到登录页面
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/"+"toLogin.do");
            return false;//程序中断
        }
        return true;//程序继续执行
    }

    @Override//执行中拦截处理的方法
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("进入了拦截");
    }

    @Override//执行后
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("在业务执行后拦截");
    }
}
