package com.mountain.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 聊天记录
 *
 * @author kejiefu
 */
@Data
public class ChatRecord implements Serializable {

    /**
     * 主键
     */
    private String id;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 接收话的用户id
     */
    private Long toUserId;
    /**
     * 聊天消息
     */
    private String msg;


}
