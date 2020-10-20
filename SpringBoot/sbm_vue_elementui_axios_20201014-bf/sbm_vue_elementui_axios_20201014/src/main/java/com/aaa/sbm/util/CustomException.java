package com.aaa.sbm.util;

/**
 * fileName:CustomException
 * description:自定义异常类
 * author:zz
 * createTime:2020/10/14 11:10
 * version:1.0.0
 */
public class CustomException  extends RuntimeException {

    //自定义异常码
    private  int errorCode;
    //自定义异常描述
    private  String errorMsg;


    /**
     * 构造
     * @param errorCode
     * @param errorMsg
     */
    public CustomException(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
