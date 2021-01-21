package com.mountain.im.transfer.service.impl;

import com.mountain.im.transfer.ApplicationTest;
import com.mountain.im.transfer.model.ChatRecord;
import com.mountain.im.transfer.service.ChatRecordService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/7 10:09
 * @Created by kejiefu
 */
public class ChatRecordServiceImplTest extends ApplicationTest {

    @Resource
    ChatRecordService chatRecordService;

    @Test
    public void sendChatRecord() {
        for (int i = 0; i < 5; i++) {
            ChatRecord chatRecordDto = new ChatRecord();
            chatRecordDto.setMsg("我是来测试的呢");
            chatRecordDto.setUserId(1L);
            chatRecordDto.setToUserId(2L);
            chatRecordService.sendChatRecord(chatRecordDto);
        }
    }

}