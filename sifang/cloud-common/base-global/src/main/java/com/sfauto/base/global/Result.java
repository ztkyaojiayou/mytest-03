package com.sfauto.base.global;


import java.io.Serializable;

public class Result<T> implements Serializable {

    private T datas;
    private Integer pageIndex;
    private Integer pageNum;
    private Long recCount;
    private CodeEnum code;
    private String msg;

    private Result(T datas, CodeEnum resp_code, String resp_msg,Integer pageIndex, Integer pageNum, Long recCount) {
        this.datas = datas;
        this.code = resp_code;
        this.msg = resp_msg;
        this.pageIndex = pageIndex;
        this.pageNum = pageNum;
        this.recCount = recCount;
    }

    private Result(T datas, CodeEnum resp_code, String resp_msg) {
        this.datas = datas;
        this.code = resp_code;
        this.msg = resp_msg;
        this.pageIndex = 0;
        this.pageNum = 0;
        this.recCount = 0L;
    }

    public static <T> Result<T> succeed(String msg) {
        return succeedWith(null, CodeEnum.SUCCESS,msg, null, null, null);
    }

    public static <T> Result<T> succeed(T model) {
        return succeedWith(model, CodeEnum.SUCCESS, "success", null, null, null);
    }

    public static <T> Result<T> succeed(T model, String msg) {
        return succeedWith(model, CodeEnum.SUCCESS, msg, null, null, null);
    }
    public static <T> Result<T> succeed(T model, String msg, Integer pageIndex, Integer pageNum, Long recCount) {
        return succeedWith(model, CodeEnum.SUCCESS, msg, pageIndex, pageNum, recCount);
    }

    public static <T> Result<T> succeedWith(T datas, CodeEnum code, String msg,Integer pageIndex, Integer pageNum, Long recCount) {
        return new Result<T>(datas, code, msg, pageIndex, pageNum, recCount);
    }

    public static <T> Result<T> failed(String msg) {
        return failedWith(null, CodeEnum.ERROR, msg);
    }

    public static <T> Result<T> failed(T model, String msg) {
        return failedWith(model, CodeEnum.ERROR, msg);
    }

    public static <T> Result<T> failedWith(T datas, CodeEnum code, String msg1) {
        return new Result<T>( datas, code, msg1);
    }

    public T getDatas() {
        return datas;
    }

    public CodeEnum getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public Long getRecCount() {
        return recCount;
    }

}
