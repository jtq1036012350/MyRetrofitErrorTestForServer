package com.marsjiang.myretrofiterrorhandle.http;

/**
 * 服务器通用返回数据格式
 * Created by jaycee on 2017/6/23.
 */
public class BaseEntity<T> {

    private int code;
    private String msg;
    private T data;

    public boolean isSuccess() {
        return code == 0;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
