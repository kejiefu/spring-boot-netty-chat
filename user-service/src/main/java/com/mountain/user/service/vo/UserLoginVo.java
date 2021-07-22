package com.mountain.user.service.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/7/20 18:34
 * @Created by kejiefu
 */
@Data
public class UserLoginVo implements Serializable {

    /**
     *  用户名称
     */
    private String username;

    /**
     * token
     */
    private String authorization;

    /**
     * 头像
     */
    private String headPortrait;


}
