package com.mountain.chat.service.service.impl;

import com.mountain.chat.service.ApplicationTest;
import com.mountain.chat.service.entity.ChatRecord;
import com.mountain.chat.service.service.ChatRecordService;
import com.mountain.chat.service.util.SequenceUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/6 21:08
 * @Created by kejiefu
 */
public class ChatRecordServiceImplTest extends ApplicationTest {

    @Autowired
    private ChatRecordService chatRecordService;

    /**
     * 测试Mybatis-Plus 新增
     */
    @Test
    public void testSave() {
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setId(SequenceUtils.getId());
        chatRecord.setContent(String.valueOf(System.currentTimeMillis()));
        chatRecord.setUserId(1L);
        chatRecord.setToUserId(2L);
        chatRecordService.save(chatRecord);
    }

}