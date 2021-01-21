package com.mountain.common.eums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/21 19:55
 * @Created by kejiefu
 */
@Getter
public enum CmdEnum {

    /**
     * 心跳
     */
    HEART_BEAT(1, "心跳");


    /**
     * code
     */
    private final Integer code;

    /**
     * name
     */
    private final String name;

    CmdEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CmdEnum getEnum(int code) {
        return Arrays.stream(values()).filter(x -> x.code.equals(code)).findFirst().orElse(null);
    }

}
