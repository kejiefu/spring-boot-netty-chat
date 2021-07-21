

package com.mountain.common.domain;

/**
 * @author kejiefu
 */
@SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
public enum ReturnCode {

    SUCCESS(200, "操作成功!"),
    EMPTY(201, "资源为空!"),
    TOKEN_IS_NULL(401, "token 不能为空"),
    INCORRECT_ACCOUNT_PASSWORD(202, "账号密码有误!"),
    TOKEN_EXPIRE(203, "登录过期，请重新登录!"),
    REQUEST_METHOD_ERROR(204, "当前接口不支持此请求方式!"),
    SERVER_ERROR(205, "请求无法处理,请稍后再试!"),
    FAIL(600, "操作失败!");

    private final Integer code;

    private final String msg;

    private ReturnCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
