package com.mountain.chat.service.listener;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.mountain.chat.service.ApplicationTest;
import com.mountain.chat.service.config.RabbitMqConfig;
import com.mountain.chat.service.entity.ChatRecord;
import com.mountain.chat.service.listener.core.MessageBody;
import com.mountain.chat.service.util.SequenceUtils;
import org.junit.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;
import java.util.Date;

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
        long id = SequenceUtils.getId();
        //消息唯一ID
        CorrelationData correlationData = new CorrelationData(id + "");
        chatRecord.setId(String.valueOf(id));
        chatRecord.setMsg("我是来测试的呢");
        chatRecord.setUserId(1L);
        chatRecord.setToUserId(2L);
        MessageBody messageBodyDto = new MessageBody();
        messageBodyDto.setCreateTime(DateUtil.format(new Date(), DatePattern.NORM_DATETIME_FORMAT));
        messageBodyDto.setData(chatRecord);
        messageBodyDto.setMessageId(String.valueOf(id));
        rabbitTemplate.convertAndSend(RabbitMqConfig.CHAT_RECORD_QUEUE, messageBodyDto, correlationData);
    }
}
