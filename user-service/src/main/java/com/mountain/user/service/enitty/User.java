package com.mountain.user.service.enitty;

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

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态，0：启用，1：禁用，2：逻辑删除
     */
    private Integer status;

}
