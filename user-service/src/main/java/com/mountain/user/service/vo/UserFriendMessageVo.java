package com.mountain.user.service.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/7/13 17:25
 * @Created by kejiefu
 */
@Data
public class UserFriendMessageVo implements Serializable {

    /**
     * 好友id
     */
    private Long friendId;

    /**
     * 用户好友名称
     */
    private String friendName;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 未读信息
     */
    private Integer unread = 1;

    /**
     * 时间信息
     */
    private String time;

    /**
     * 最后的消息
     */
    private String summary = "点击发送消息";

}
