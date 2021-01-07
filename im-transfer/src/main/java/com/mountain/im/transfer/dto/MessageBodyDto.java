package com.mountain.im.transfer.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author kejiefu
 */
@Data
@Slf4j
public class MessageBodyDto implements Serializable {

    private String messageId;
    private String createTime;
    private Object data;

    public MessageBodyDto() {
        messageId = String.valueOf(UUID.randomUUID());
        createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public MessageBodyDto(Object data) {
        this();
        this.data = data;
    }

    public <T> T getData(Class<T> type) {
        return JSON.parseObject(this.getData().toString(), type);
    }

    public static MessageBodyDto getMessageBody(Message message) {
        MessageBodyDto messageBody = null;
        try {
            String s = new String(message.getBody(), "utf-8");
            messageBody = JSON.parseObject(s, MessageBodyDto.class);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return messageBody;
    }

    public static <T> T getMessageData(Message message, Class<T> type) {
        return MessageBodyDto.getMessageBody(message).getData(type);
    }
}