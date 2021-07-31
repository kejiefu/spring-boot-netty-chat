package com.mountain.common.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/21 19:55
 * @Created by kejiefu
 */
@Getter
public enum ProtobufDataTypeEnum {

    /**
     * 普通信息
     */
    Common_MESSAGE(1, "普通信息"),

    /**
     * 心跳
     */
    HEART_BEAT(2, "心跳");


    /**
     * code
     */
    private final Integer code;

    /**
     * name
     */
    private final String name;

    ProtobufDataTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ProtobufDataTypeEnum getEnum(int code) {
        return Arrays.stream(values()).filter(x -> x.code.equals(code)).findFirst().orElse(null);
    }

}
