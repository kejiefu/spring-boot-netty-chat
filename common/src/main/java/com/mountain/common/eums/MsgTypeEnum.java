package com.mountain.common.eums;

import lombok.Getter;

import java.util.Arrays;

/**
 * 回执数据格式ACK，默认已发送，0：sent（已发送），1：delivered（已送达）, 2：read（已读）
 *
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/18 21:01
 * @Created by kejiefu
 */
@Getter
public enum MsgTypeEnum {

    /**
     * 0：sent（已发送）
     */
    SENT(0, "已发送"),

    /**
     * 1：delivered（已送达）
     */
    DELIVERED(1, "已送达"),

    /**
     * 2：read（已读）
     */
    READ(2, "已读");

    /**
     * code
     */
    private final Integer code;

    /**
     * name
     */
    private final String name;

    MsgTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static MsgTypeEnum getEnum(int code) {
        return Arrays.stream(values()).filter(x -> x.code.equals(code)).findFirst().orElse(null);
    }

}
