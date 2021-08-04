package com.mountain.im.transfer.handler;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ByteString;
import com.mountain.common.domain.ChatContent;
import com.mountain.common.domain.ProtobufData;
import com.mountain.common.enums.ProtobufDataTypeEnum;
import com.mountain.im.transfer.config.RabbitMqConfig;
import com.mountain.im.transfer.model.ChatRecord;
import com.mountain.im.transfer.model.MessageBody;
import com.mountain.im.transfer.model.protobuf.BaseMessageProto;
import com.mountain.im.transfer.util.SpringContextUtils;
import com.mountain.im.transfer.util.UserUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author kejiefu
 */
@Slf4j
public class TransferHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * 这条连接上添加的所有的业务逻辑处理器都被移除掉后调用
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        log.info("handlerRemoved...{}", ctx);
    }

    /**
     * 因于Netty的I/O异常或一个处理器实现的内部异常。多数情况下，捕捉到的异常应当被记录下来，并在这个方法中关闭这个channel通道。
     * 当然处理这种异常情况的方法实现可能因你的实际需求而有所不同，例如，在关闭这个连接之前你可能会发送一个包含了错误码的响应消息。
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("exceptionCaught...", cause);
        ctx.close();
    }

    /**
     * 客户端退出通知事件（channelInactive）
     *
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.error("channelInactive...");
    }

    /**
     * 接收消息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        log.info("transfer.TransferHandler.channelRead0:{}", msg);
        if (msg instanceof BaseMessageProto.BaseMessage) {
            String stringUtf8 = ((BaseMessageProto.BaseMessage) msg).getData().toStringUtf8();
            ProtobufData protobufData = JSONObject.parseObject(stringUtf8, ProtobufData.class);
            log.info("protobufData:{},currentTimeMillis:{}", protobufData, System.currentTimeMillis());
            RabbitTemplate rabbitTemplate = SpringContextUtils.getBean(RabbitTemplate.class);
            UserUtils userUtils = SpringContextUtils.getBean(UserUtils.class);
            BaseMessageProto.BaseMessage.Builder builder = BaseMessageProto.BaseMessage.newBuilder();
            if (protobufData.getType().equals(ProtobufDataTypeEnum.Common_MESSAGE.getCode())) {
                ProtobufData protobufData1 = new ProtobufData();
                protobufData1.setType(ProtobufDataTypeEnum.HEART_BEAT.getCode());
                protobufData1.setContent("pong");
                protobufData1.setTime(System.currentTimeMillis());
                protobufData1.setId(protobufData.getId());
                String jsonString = JSONObject.toJSONString(protobufData1);
                ByteString bytes = ByteString.copyFrom(jsonString, "UTF-8");
                builder.setData(bytes);
                BaseMessageProto.BaseMessage message = builder.build();
                log.info("发送心跳回应消息到connector,{}", jsonString);
                ctx.writeAndFlush(message);
            } else if (protobufData.getType().equals(ProtobufDataTypeEnum.Common_MESSAGE.getCode())) {
                ChatContent chatContent = JSONObject.parseObject(protobufData.getContent(), ChatContent.class);
                ChatRecord chatRecord = new ChatRecord();
                CorrelationData correlationData = new CorrelationData(protobufData.getId());
                chatRecord.setId(protobufData.getId());
                chatRecord.setMsg(chatContent.getMessage());
                chatRecord.setUserId(userUtils.getUserId(chatContent.getToken()));
                chatRecord.setToUserId(Long.valueOf(chatContent.getFriendId()));
                MessageBody messageBody = new MessageBody();
                messageBody.setCreateTime(DateUtil.format(new Date(), DatePattern.NORM_DATETIME_FORMAT));
                messageBody.setData(chatRecord);
                messageBody.setMessageId(protobufData.getId());
                rabbitTemplate.convertAndSend(RabbitMqConfig.CHAT_RECORD_QUEUE, messageBody, correlationData);
            } else if (protobufData.getType().equals(ProtobufDataTypeEnum.GROUP_MESSAGE.getCode())) {

            }
        }
    }

}
