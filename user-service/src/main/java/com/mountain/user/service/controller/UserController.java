package com.mountain.user.service.controller;

import com.mountain.common.domain.Result;
import com.mountain.user.service.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/11 21:31
 * @Created by kejiefu
 */
@Api(value = "user", tags = "user")
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "用户密码")})
    public Result<Void> login(@RequestParam String username, @RequestParam String password) {
        return null;
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public Result<Void> logout() {
        return null;
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "用户密码")})
    public Result<Boolean> register(@RequestParam String username, @RequestParam String password) {
        return userService.register(username, password);
    }

}
