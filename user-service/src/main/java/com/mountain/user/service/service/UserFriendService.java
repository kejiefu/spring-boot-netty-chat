package com.mountain.user.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mountain.common.domain.Result;
import com.mountain.user.service.entity.UserFriend;
import com.mountain.user.service.vo.UserFriendMessageVo;

import java.util.List;

/**
 * 用户好友表(TUserFriend)表服务接口
 *
 * @author kejiefu
 * @since 2021-07-13 17:08:29
 */
public interface UserFriendService extends IService<UserFriend> {


    Result<List<UserFriendMessageVo>> listUserFriendMessageVo(Long userId);

}
