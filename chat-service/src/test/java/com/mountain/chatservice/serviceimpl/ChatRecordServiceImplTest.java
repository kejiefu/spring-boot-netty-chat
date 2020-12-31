package com.mountain.chatservice.serviceimpl;

import com.mountain.chatservice.ApplicationTests;
import com.mountain.chatservice.entity.ChatRecord;
import com.mountain.chatservice.service.ChatRecordService;
import com.mountain.chatservice.util.SequenceUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2020/12/31 14:56
 * @Created by kejiefu
 */
public class ChatRecordServiceImplTest extends ApplicationTests {

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