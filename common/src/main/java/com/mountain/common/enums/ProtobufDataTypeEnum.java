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
     * 心跳
     */
    HEART_BEAT(1, "心跳"),

    /**
     * 单聊消息
     */
    COMMON_MESSAGE(2, "单聊消息"),

    /**
     * 群聊消息
     */
    GROUP_MESSAGE(3, "群聊消息");



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
