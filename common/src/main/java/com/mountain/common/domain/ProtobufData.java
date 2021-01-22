package com.mountain.common.domain;

import lombok.Data;

/**
 * @author kejiefu
 */
@Data
public class ProtobufData {
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
