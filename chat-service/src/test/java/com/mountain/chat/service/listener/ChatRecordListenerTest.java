package com.mountain.chat.service.listener;

import com.alibaba.fastjson.JSONObject;
import com.mountain.chat.service.ApplicationTest;
import com.mountain.chat.service.config.RabbitMqConfig;
import com.mountain.chat.service.entity.ChatRecord;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/6 21:04
 * @Created by kejiefu
 */
public class ChatRecordListenerTest extends ApplicationTest {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Test
    public void consume() {
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setId(System.currentTimeMillis());
        rabbitTemplate.convertAndSend(RabbitMqConfig.CHAT_RECORD_QUEUE, chatRecord);
        System.out.println(JSONObject.toJSONString(chatRecord));
    }
}