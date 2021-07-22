package com.mountain.user.service.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mountain.common.domain.Result;
import com.mountain.user.service.ApplicationTest;
import com.mountain.user.service.service.UserFriendService;
import com.mountain.user.service.vo.UserFriendMessageVo;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/7/19 15:29
 * @Created by kejiefu
 */
public class UserFriendServiceImplTest extends ApplicationTest {

    @Resource
    UserFriendService userFriendService;


    @Test
    public void listUserFriendMessageVo() {
        Result<List<UserFriendMessageVo>> listResult = userFriendService.listUserFriendMessageVo();
        System.out.println(JSONObject.toJSONString(listResult));
    }

}
