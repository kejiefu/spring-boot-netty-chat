package com.mountain.common.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * 在线状态，0：不在线，1：在线
 *
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/14 16:56
 * @Created by kejiefu
 */
@Getter
public enum OnlineStatusEnum {

    /**
     * 不在线
     */
    NO(0, "不在线"),

    /**
     * 在线
     */
    YES(1, "在线");

    /**
     * code
     */
    private final Integer code;

    /**
     * name
     */
    private final String name;

    OnlineStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static OnlineStatusEnum getEnum(int code) {
        return Arrays.stream(values()).filter(x -> x.code.equals(code)).findFirst().orElse(null);
    }

}
