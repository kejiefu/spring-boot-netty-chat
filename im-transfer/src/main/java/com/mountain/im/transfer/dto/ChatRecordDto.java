package com.mountain.im.transfer.dto;

import lombok.Data;

/**
 * 聊天记录
 *
 * @author kejiefu
 */
@Data
public class ChatRecordDto {

    /**
     * 主键
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 接收话的用户id
     */
    private String toUserId;
    /**
     * 内容
     */
    private String content;


}
