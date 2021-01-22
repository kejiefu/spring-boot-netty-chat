package com.mountain.im.connector.handler.transfer;

import com.alibaba.fastjson.JSONObject;
import com.mountain.common.domain.ProtobufData;
import com.mountain.im.connector.model.protobuf.BaseMessageProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TransferHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        log.info("handlerRemoved...");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        log.info("connector.TransferHandler.channelRead0:{}", msg);
        if (msg instanceof BaseMessageProto.BaseMessage) {
            String stringUtf8 = ((BaseMessageProto.BaseMessage) msg).getData().toStringUtf8();
            ProtobufData protobufData = JSONObject.parseObject(stringUtf8, ProtobufData.class);
            log.info("收到心跳回应消息,protobufData:{},currentTimeMillis:{}", protobufData, System.currentTimeMillis());
        }
    }

}
