package com.sfauto.base.global;


public enum CodeEnum {
    SUCCESS(0),
    ERROR(1),
    EXCEPTION(2, "服务异常");

    private Integer code;
    private String message;

    CodeEnum(Integer code){
        this.code = code;
        if(code == 0){
            message = "操作成功";
        }else{
            message = "操作失败";
        }
    }

    CodeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
