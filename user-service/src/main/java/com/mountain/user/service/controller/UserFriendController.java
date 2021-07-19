package com.mountain.user.service.controller;

import com.mountain.common.domain.Result;
import com.mountain.user.service.service.UserFriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户好友表(TUserFriend)表控制层
 *
 * @author kejiefu
 * @since 2021-07-13 17:08:33
 */
@Api(value = "userFriend", tags = "userFriend")
@RestController
@RequestMapping("/user-friend")
public class UserFriendController  {

    /**
     * 服务对象
     */
    @Autowired
    private UserFriendService userFriendService;

    @ApiOperation("好友信息")
    @PostMapping("/message")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id"),})
    public Result message(@RequestParam Long userId) {
        return userFriendService.listUserFriendMessageVo(userId);
    }

}
