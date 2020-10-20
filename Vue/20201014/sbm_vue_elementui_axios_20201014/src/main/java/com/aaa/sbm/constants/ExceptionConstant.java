package com.aaa.sbm.constants;

/**
 * fileName:ExceptionConstant
 * description:
 * author:zz
 * createTime:2020/10/14 11:13
 * version:1.0.0
 */
public enum ExceptionConstant {
    SYS_EXCEPTION(5000, "系统处理异常"),
    ID_NOT_NULL(5001, "ID不能为NULL"),
    FIND_MODEL_BY_ID_ERROR(5002, "根据id查询数据时异常"),
    UNKOWN_EXCEPTION(5003, "未知异常"),
    DATA_NOT_EXIST(5004, "数据不存在"),
    INVALID_ARGUMENT(5005, "无效的参数"),
    TOKEN_NOT_NULL(5006, "token不能为NULL"),
    NO_PERMISSION_ACCESS(5007, "无权查看该内容");
    //......可以自定义任何异常

    private int errorCode;
    //错误信息描述
    private String errorMessage;

    ExceptionConstant(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
