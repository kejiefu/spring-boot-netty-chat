package com.mountain.user.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mountain.user.service.entity.UserFriend;
import org.springframework.stereotype.Repository;

/**
 * 用户好友表(TUserFriend)表数据库访问层
 *
 * @author kejiefu
 * @since 2021-07-13 17:08:27
 */
@Repository
public interface UserFriendMapper extends BaseMapper<UserFriend> {

}
