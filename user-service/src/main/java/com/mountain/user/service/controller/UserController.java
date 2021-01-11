package com.mountain.user.service.controller;

import com.mountain.common.domain.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/11 21:31
 * @Created by kejiefu
 */
@Controller
public class UserController {

    @ApiOperation("登录")
    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "用户ID")})
    public Result login(@RequestParam String username, @RequestParam String password) {
        return null;
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public Result logout() {
        return null;
    }


}
