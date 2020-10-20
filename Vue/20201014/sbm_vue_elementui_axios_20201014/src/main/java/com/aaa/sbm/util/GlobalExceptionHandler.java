package com.aaa.sbm.util;

import com.aaa.sbm.constants.ReturnStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * fileName:GlobalExceptionHandler
 * description:
 * author:zz
 * createTime:2020/10/14 11:18
 * version:1.0.0
 */
@RestControllerAdvice // 统一处理异常，类初始化或者绑定属性  增强注解  所有的requestMapping 都会经过
public class GlobalExceptionHandler {

    /**
     * 统一异常处理方法
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result  handlerException(Exception ex){
        //处理自定义异常
        if(ex instanceof CustomException){ //判断异常是否是自定义异常
            CustomException customException = (CustomException)ex;
            return new Result(customException.getErrorCode(),
                    customException.getErrorMsg());
        }
        //处理通用异常
        return new Result<String>(ReturnStatus.FAIL.getReturnCode(),
                ReturnStatus.FAIL.getReturnMsg(),
                ex.getClass().getName());
    }
}
