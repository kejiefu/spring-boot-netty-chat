package com.mountain.user.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mountain.common.domain.Result;
import com.mountain.user.service.entity.User;

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
     * @param username 账号
     * @param password 密码
     * @return Result
     */
    Result<Boolean> register(String username, String password);

    /**
     * 登录
     *
     * @param username 账号
     * @param password 密码
     * @return Result
     */
    Result login(String username, String password);

    /**
     * 获取user
     *
     * @param id id
     * @return
     */
    Result<User> getUser(String id);

}
