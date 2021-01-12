package com.mountain.user.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mountain.common.domain.Result;
import com.mountain.user.service.enitty.User;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/11 21:34
 * @Created by kejiefu
 */
public interface UserService extends IService<User> {

    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    Result<Boolean> register(String username, String password);

}
