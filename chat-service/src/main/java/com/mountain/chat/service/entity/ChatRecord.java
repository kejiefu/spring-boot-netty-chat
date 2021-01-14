package com.mountain.chat.service.entity;

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
