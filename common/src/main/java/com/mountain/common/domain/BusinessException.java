package com.mountain.common.domain;


import lombok.Data;

/**
 * created by @author wangzelong 2020/6/3 10:45
 * 业务异常
 */
@Data
public class BusinessException extends RuntimeException {
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 提示信息.
     */
    private String msg;
    /**
     * 业务异常数据
     */
    private Object data;

    public BusinessException() {
        this.code = ReturnCode.FAIL.getCode();
        this.msg = ReturnCode.FAIL.getMsg();
    }

    public BusinessException(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BusinessException(ReturnCode returnCode) {
        this.code = returnCode.getCode();
        this.msg = returnCode.getMsg();
    }

    public BusinessException(ReturnCode returnCode, Object data) {
        this.code = returnCode.getCode();
        this.msg = returnCode.getMsg();
        this.data = data;
    }

    public BusinessException(Result result) {
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.data = result.getData();
    }
}
