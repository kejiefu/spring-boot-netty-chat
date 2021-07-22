package com.mountain.user.service.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mountain.common.domain.Result;
import com.mountain.user.service.ApplicationTest;
import com.mountain.user.service.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/12 21:59
 * @Created by kejiefu
 */
public class UserServiceImplTest extends ApplicationTest {

    @Resource
    UserService userService;

    @Test
    public void register() {
        String username = "ke";
        String password = DigestUtils.md5Hex("123456");
        Result<Boolean> result = userService.register(username, password);
        System.out.println(JSONObject.toJSONString(result));
    }

    @Test
    public void login() {
        String username = "柯";
        String password = DigestUtils.md5Hex("123456");
        Result result = userService.login(username, password);
        System.out.println(JSONObject.toJSONString(result));
    }

    @Test
    public void getUser() {
        Result result = userService.getUser("1349643297211699201");
        System.out.println(JSONObject.toJSONString(result));
    }
}
