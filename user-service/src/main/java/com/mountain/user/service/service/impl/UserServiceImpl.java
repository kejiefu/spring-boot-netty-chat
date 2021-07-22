package com.mountain.user.service.service.impl;

import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.mountain.common.domain.Result;
import com.mountain.common.domain.ReturnCode;
import com.mountain.common.eums.OnlineStatusEnum;
import com.mountain.common.eums.UserStatusEnum;
import com.mountain.user.service.entity.User;
import com.mountain.user.service.mapper.UserMapper;
import com.mountain.user.service.service.UserService;
import com.mountain.user.service.util.SequenceUtils;
import com.mountain.user.service.vo.UserLoginVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

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

    private final String keys = "abc@123456";

    private final byte[] key = keys.getBytes();

    /**
     * 注册
     *
     * @param username 账号
     * @param password 密码
     * @return Result
     */
    @Override
    public Result<Boolean> register(String username, String password) {
        User user = new User();
        user.setId(SequenceUtils.getId());
        user.setUsername(username);
        user.setPassword(password);
        user.setStatus(UserStatusEnum.YES.getCode());
        user.setOnlineStatus(OnlineStatusEnum.NO.getCode());
        return Result.success(this.save(user));
    }


    /**
     * 登录
     *
     * @param username 账号
     * @param password 密码
     * @return Result
     */
    @Override
    public Result login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, username);
        queryWrapper.lambda().eq(User::getPassword, password);
        queryWrapper.lambda().eq(User::getStatus, UserStatusEnum.YES.getCode());
        User user = this.getOne(queryWrapper);
        if (Objects.nonNull(user)) {
            //更新在线状态
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda().set(User::getOnlineStatus, OnlineStatusEnum.YES.getCode());
            updateWrapper.lambda().eq(User::getId, user.getId());
            this.update(updateWrapper);
            //生成token
            Map<String, Object> payload = Maps.newHashMap();
            payload.put("userId", user.getId());
            payload.put("userName", user.getUsername());

            String token = JWTUtil.createToken(payload, key);

            UserLoginVo userLoginVo = new UserLoginVo();
            userLoginVo.setAuthorization(token);
            userLoginVo.setUsername(user.getUsername());

            return Result.success(userLoginVo);
        }
        return Result.fail(ReturnCode.INCORRECT_ACCOUNT_PASSWORD);
    }

    /**
     * 获取user
     *
     * @param id id
     * @return
     */
    @Override
    public Result<User> getUser(String id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getId, id);
        queryWrapper.lambda().eq(User::getStatus, UserStatusEnum.YES.getCode());
        User user = this.getOne(queryWrapper);
        return Result.success(user);
    }


}
