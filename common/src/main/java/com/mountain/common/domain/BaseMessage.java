package com.mountain.common.domain;

import lombok.Data;

/**
 * @author kejiefu
 */
@Data
public class BaseMessage {
    private Integer cmd;
    private String data;
}
