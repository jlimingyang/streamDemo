package com.test;


import java.io.Serializable;

public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int SUCCESS_CODE = 0;
    public static final int FAILED_CODE = 1;
    public static final int UNKNOWN_CODE = 2;
    //未登录
    public static final int UN_LOGIN = 3;
    //无权限
    public static final int UN_AUTH = 4;



    public static final String SUCCESS_MESSAGE = "success";
    public static final String FAILED_MESSAGE = "failed";
    public static final String UNKNOWN_MESSAGE = "unknown";

    private Integer code = SUCCESS_CODE;
    private String message = SUCCESS_MESSAGE;
    private T data;

    public JsonResult(){}

    public JsonResult(T data){
        this.data = data;
    }

    public JsonResult(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public JsonResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public JsonResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public JsonResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static JsonResult<String> getSuccessInstance() {
        return new JsonResult<String>().setData("");
    }

    public static JsonResult<String> getFailedInstance() {
        return new JsonResult<String>(FAILED_CODE,FAILED_MESSAGE,"");
    }

}
