package com.mountain.common.eums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/12 21:48
 * @Created by kejiefu
 */
@Getter
public enum UserStatusEnum {

    /**
     * 未删除
     */
    YES(0, "启用"),

    /**
     * 禁用
     */
    NO(1, "禁用"),

    /**
     * 逻辑删除
     */
    DELETE(2, "逻辑删除");

    /**
     * code
     */
    private final Integer code;

    /**
     * name
     */
    private final String name;

    UserStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static UserStatusEnum getEnum(int code) {
        return Arrays.stream(values()).filter(x -> x.code.equals(code)).findFirst().orElse(null);
    }

}
