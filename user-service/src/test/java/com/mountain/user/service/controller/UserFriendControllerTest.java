package com.mountain.user.service.controller;

import cn.hutool.http.HttpUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/7/19 15:41
 * @Created by kejiefu
 */
public class UserFriendControllerTest {

    @Test
    public void message() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", 1349643297211699201L);
        String result = HttpUtil.post("http://127.0.0.1:18085/user-friend/message", paramMap);
        System.out.println(result);
    }
}
