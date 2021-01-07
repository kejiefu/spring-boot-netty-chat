package com.mountain.chat.service.listener;


import com.mountain.chat.service.config.RabbitMqConfig;
import com.mountain.chat.service.entity.ChatRecord;
import com.mountain.chat.service.listener.core.Action;
import com.mountain.chat.service.listener.core.MessageBody;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/1/5 20:14
 * @Created by kejiefu
 */
@Component
@Slf4j
public class ChatRecordListener {

    /**
     * 监听单个队列
     */
    @RabbitListener(queues = RabbitMqConfig.CHAT_RECORD_QUEUE)
    @RabbitHandler
    public void consume(Message message, Channel channel) throws IOException {
        log.info("接收单个聊天队列消息");
        messageHandler(message, channel);
    }

    private void messageHandler(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        Action action = Action.ACCEPT;
        String messageId = "";
        try {
            MessageBody messageBody = MessageBody.getMessageBody(message);
            messageId = messageBody.getMessageId();
            log.info("接收到消息Body：{}", messageBody.toString());
            ChatRecord chatRecord = MessageBody.getMessageData(message, ChatRecord.class);
            log.info("接收到消息Data：{}", chatRecord.toString());
        } catch (Exception e) {
            action = Action.REJECT;
            log.error("处理消息出错", e);
        } finally {
            // 通过 finally 块来保证 Ack/Nack 会且只会执行一次
            if (action == Action.ACCEPT) {
                // false 只确认当前 consumer 一个消息收到，true 确认所有 consumer 获得的消息。
                channel.basicAck(deliveryTag, false);
                log.info("处理消息成功");
            } else {
                // 第二个 boolean 为 false 表示不会重试，为 true 会重新放回队列
                // 如果绑定了死信队列，会放入死信队列
                channel.basicReject(deliveryTag, false);
            }
        }
    }

}
