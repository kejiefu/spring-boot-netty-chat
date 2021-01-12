package com.mountain.user.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mountain.common.domain.Result;
import com.mountain.common.eums.UserStatusEnum;
import com.mountain.user.service.enitty.User;
import com.mountain.user.service.mapper.UserMapper;
import com.mountain.user.service.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/11 21:34
 * @Created by kejiefu
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public Result<Boolean> register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setStatus(UserStatusEnum.YES.getCode());
        return Result.success(this.save(user));
    }

}
