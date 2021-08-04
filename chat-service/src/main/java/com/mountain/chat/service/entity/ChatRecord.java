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
    private Long userId;

    /**
     * 接收话的用户id
     */
    private Long toUserId;

    /**
     * 聊天消息
     */
    private String msg;

    /**
     * 回执数据格式ACK，默认已发送，0：sent（已发送），1：delivered（已送达）, 2：read（已读）
     */
    private Integer msgType;


}
