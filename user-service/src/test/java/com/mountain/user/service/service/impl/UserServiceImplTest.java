package com.mountain.user.service.service.impl;

import com.mountain.user.service.ApplicationTest;
import com.mountain.user.service.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

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
        String username = "";
        String password = "";
        userService.register(username, password);
    }
}