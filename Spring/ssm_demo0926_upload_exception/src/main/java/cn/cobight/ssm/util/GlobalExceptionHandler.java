package cn.cobight.ssm.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * fileName:GlobalExceptionHandler
 * description:
 * author:cobight
 * createTime:2020/9/28 11:32
 * version:1.0.0
 */
//@Component//xml配置或者加注解
public class GlobalExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView("default-error");
        httpServletRequest.setAttribute("ex",e);//手动装填异常
        return modelAndView;
    }
}
