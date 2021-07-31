package com.mountain.common.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/12 11:00
 * @Created by kejiefu
 */
@Getter
public enum DeleteEnum {

    /**
     * 未删除
     */
    NO(0, "未删除"),

    /**
     * 已删除
     */
    YES(1, "已删除");

    /**
     * code
     */
    private final Integer code;

    /**
     * name
     */
    private final String name;

    DeleteEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static DeleteEnum getEnum(int code) {
        return Arrays.stream(values()).filter(x -> x.code.equals(code)).findFirst().orElse(null);
    }

}
