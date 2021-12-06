package com.mountain.im.transfer.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.mountain.common.domain.ChatRecord;
import com.mountain.im.transfer.config.RabbitMqConfig;
import com.mountain.im.transfer.model.MessageBody;
import com.mountain.im.transfer.service.ChatRecordService;
import com.mountain.im.transfer.util.SequenceUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/7 10:03
 * @Created by kejiefu
 */
@Service
public class ChatRecordServiceImpl implements ChatRecordService {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendChatRecord(ChatRecord chatRecordDto) {
        String id = String.valueOf(SequenceUtils.getId());
        //消息唯一ID
        CorrelationData correlationData = new CorrelationData(id);
        chatRecordDto.setId(id);
        MessageBody messageBodyDto = new MessageBody();
        messageBodyDto.setCreateTime(DateUtil.format(new Date(), DatePattern.NORM_DATETIME_FORMAT));
        messageBodyDto.setData(chatRecordDto);
        messageBodyDto.setMessageId(id);
        rabbitTemplate.convertAndSend(RabbitMqConfig.CHAT_RECORD_QUEUE, messageBodyDto, correlationData);
    }

}
