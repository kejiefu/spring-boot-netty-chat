package com.mountain.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/8/3 18:14
 * @Created by kejiefu
 */
@Data
public class ChatContent implements Serializable {

    /**
     * token
     */
    private String token;

    /**
     * 用户id
     */
    private String friendId;

    /**
     * 群id
     */
    private String groupId;

    /**
     * 信息
     */
    private String message;

}
