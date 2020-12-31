package com.mountain.chatservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 聊天记录
 *
 * @author kejiefu
 */
@Data
@TableName(value = "t_chat_record")
public class ChatRecord extends BaseEntity {

    /**
     * 主键
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 接收话的用户id
     */
    private Long toUserId;
    /**
     * 内容
     */
    private String content;


}
