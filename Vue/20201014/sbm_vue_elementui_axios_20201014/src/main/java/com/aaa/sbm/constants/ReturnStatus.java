package com.aaa.sbm.constants;

/**
 * fileName:ReturnStatus
 * description:
 * author:zz
 * createTime:2020/10/14 11:00
 * version:1.0.0
 */
public enum ReturnStatus {

    SUCCESS(2000,"操作成功"),
    FAIL(3000,"操作失败");

    private int returnCode;
    private String returnMsg;

    /**
     * 构造
     * @param returnCode
     * @param returnMsg
     */
    ReturnStatus(int returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}
