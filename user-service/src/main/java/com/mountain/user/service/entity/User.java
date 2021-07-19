package com.mountain.user.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/11 21:35
 * @Created by kejiefu
 */
@Data
@TableName(value = "t_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = -76174557502379397L;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 在线状态，0：不在线，1：在线
     */
    private Integer onlineStatus;

    /**
     * 状态，0：启用，1：禁用，2：逻辑删除
     */
    private Integer status;

    /**
     * 头像
     */
    private String headPortrait;

}
