package com.mountain.user.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 用户好友表(TUserFriend)实体类
 *
 * @author kejiefu
 * @since 2021-07-13 17:08:48
 */
@Data
@TableName(value = "t_user_friend")
public class UserFriend extends BaseEntity {

    private static final long serialVersionUID = -76174557502379397L;

    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @NotEmpty
    private Long id;

    /**
     * 用户id,t_user.id
     */
    private Long userId;

    /**
     * 好友id
     */
    private Long friendId;


}
