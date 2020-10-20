package cn.cobight.sbm.util;

/**
 * fileName:Result
 * description:
 * author:cobight
 * createTime:2020/10/13 15:13
 * version:1.0.0
 */
public class Result<T> {
    //响应码
    private int code;
    //响应信息
    private String message;
    //响应数据
    private T data;

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
