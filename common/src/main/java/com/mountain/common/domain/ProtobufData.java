package com.mountain.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kejiefu
 */
@Data
public class ProtobufData implements Serializable {
    /**
     * 类型
     */
    private Integer type;
    /**
     * 数据
     */
    private String content;
    /**
     * 时间
     */
    private Long time;
}
