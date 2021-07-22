package com.mountain.user.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.mountain.common.domain.Result;
import com.mountain.user.service.entity.User;
import com.mountain.user.service.entity.UserFriend;
import com.mountain.user.service.mapper.UserFriendMapper;
import com.mountain.user.service.service.UserFriendService;
import com.mountain.user.service.service.UserService;
import com.mountain.user.service.util.UserUtils;
import com.mountain.user.service.vo.UserFriendMessageVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户好友表(TUserFriend)表服务实现类
 *
 * @author kejiefu
 * @since 2021-07-13 17:08:31
 */
@Service("userFriendService")
public class UserFriendServiceImpl extends ServiceImpl<UserFriendMapper, UserFriend> implements UserFriendService {

    @Resource
    private UserFriendMapper userFriendMapper;

    @Resource
    private UserService userService;

    @Resource
    UserUtils userUtils;

    @Override
    public Result<List<UserFriendMessageVo>> listUserFriendMessageVo() {
        Result<List<UserFriendMessageVo>> listResult = new Result<>();
        List<UserFriendMessageVo> userFriendMessageVoList = Lists.newArrayListWithCapacity(16);
        //获取好友数据
        QueryWrapper<UserFriend> userFriendQueryWrapper = new QueryWrapper<>();
        userFriendQueryWrapper.lambda().eq(UserFriend::getUserId, userUtils.getUserId());
        userFriendQueryWrapper.lambda().eq(UserFriend::getIsDelete, 0);
        List<UserFriend> userFriendList = this.list(userFriendQueryWrapper);
        if (!CollectionUtils.isEmpty(userFriendList)) {
            List<Long> friendIdList = userFriendList.stream().map(UserFriend::getFriendId).collect(Collectors.toList());
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.lambda().in(User::getId, friendIdList);
            userQueryWrapper.lambda().select(User::getHeadPortrait, User::getId, User::getUsername);
            List<User> userList = userService.list(userQueryWrapper);
            for (UserFriend userFriend : userFriendList) {
                UserFriendMessageVo userFriendMessageVo = new UserFriendMessageVo();
                userFriendMessageVo.setFriendId(userFriend.getFriendId());
                Optional<User> userOptional = userList.stream().filter(t -> t.getId().equals(userFriend.getFriendId())).findFirst();
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    userFriendMessageVo.setFriendName(user.getUsername());
                    userFriendMessageVo.setHeadPortrait(user.getHeadPortrait());
                    userFriendMessageVo.setOnlineStatus(user.getOnlineStatus());
                    userFriendMessageVoList.add(userFriendMessageVo);
                }
            }
        }
        listResult.setData(userFriendMessageVoList);
        return listResult;
    }

}
