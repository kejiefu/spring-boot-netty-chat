package com.mountain.user.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/7/20 20:42
 * @Created by kejiefu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;



}
