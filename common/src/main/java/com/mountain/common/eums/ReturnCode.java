

package com.mountain.common.eums;

/**
 *
 * @author kejiefu
 */
@SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
public enum ReturnCode {

    success(200, "操作成功"),
    empty(201, "资源为空"),
    tokenExpire(401, "登录过期，请重新登录"),
    REQUEST_METHOD_ERROR(403, "当前接口不支持此请求方式!"),
    serverError(500, "请求无法处理,请稍后再试！"),
    fail(600, "操作失败");

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
