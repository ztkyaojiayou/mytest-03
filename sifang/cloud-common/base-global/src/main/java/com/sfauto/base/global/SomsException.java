package com.sfauto.base.global;

public class SomsException extends RuntimeException{

    private Integer code;

    public SomsException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SomsException(CodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    public SomsException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "CMSException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }

}
