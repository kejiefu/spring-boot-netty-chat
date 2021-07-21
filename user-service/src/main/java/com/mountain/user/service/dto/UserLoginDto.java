package com.mountain.user.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/7/20 18:32
 * @Created by kejiefu
 */
@Data
public class UserLoginDto implements Serializable {

    private String username;

    private String password;

}
