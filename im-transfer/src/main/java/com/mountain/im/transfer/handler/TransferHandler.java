package com.mountain.im.transfer.handler;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ByteString;
import com.mountain.common.domain.ProtobufData;
import com.mountain.common.eums.ProtobufDataTypeEnum;
import com.mountain.im.transfer.model.protobuf.BaseMessageProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

/**
 * @author kejiefu
 */
@Slf4j
public class TransferHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        log.info("handlerRemoved...");
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
            //组装发送心跳回应消息
            BaseMessageProto.BaseMessage.Builder builder = BaseMessageProto.BaseMessage.newBuilder();
            ProtobufData protobufData1 = new ProtobufData();
            protobufData1.setType(ProtobufDataTypeEnum.HEART_BEAT.getCode());
            protobufData1.setContent("我已经收到心跳信息");
            protobufData1.setTime(System.currentTimeMillis());
            String jsonString = JSONObject.toJSONString(protobufData1);
            ByteString bytes = ByteString.copyFrom(jsonString, "UTF-8");
            builder.setData(bytes);
            BaseMessageProto.BaseMessage message = builder.build();
            log.info("发送心跳回应消息到connector,{}", jsonString);
            ctx.writeAndFlush(message);
        }
    }

}
