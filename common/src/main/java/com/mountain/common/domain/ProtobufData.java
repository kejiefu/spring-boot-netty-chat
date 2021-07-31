package com.mountain.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kejiefu
 */
@Data
public class ProtobufData implements Serializable {
    /**
     * 唯一消息id
     */
    private String id;
    /**
     * 类型
     * {@link com.mountain.common.enums.ProtobufDataTypeEnum}
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
