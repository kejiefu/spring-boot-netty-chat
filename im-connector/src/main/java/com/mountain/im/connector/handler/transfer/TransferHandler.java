package com.mountain.im.connector.handler.transfer;

import com.alibaba.fastjson.JSONObject;
import com.mountain.common.domain.ChatRecord;
import com.mountain.common.domain.ProtobufData;
import com.mountain.common.enums.ProtobufDataTypeEnum;
import com.mountain.im.connector.factory.TransferFactory;
import com.mountain.im.connector.handler.client.ClientChanel;
import com.mountain.im.connector.handler.client.ClientChanelServer;
import com.mountain.im.connector.model.protobuf.BaseMessageProto;
import com.mountain.im.connector.transfer.TransferChannel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kejiefu
 */
@Slf4j
public class TransferHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        log.info("handlerRemoved...");
        TransferFactory.getInstance().onClose(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        log.info("connector.TransferHandler.channelRead0:{}", msg);
        if (msg instanceof BaseMessageProto.BaseMessage) {
            String stringUtf8 = ((BaseMessageProto.BaseMessage) msg).getData().toStringUtf8();
            ProtobufData protobufData = JSONObject.parseObject(stringUtf8, ProtobufData.class);
            log.info("收到消息,protobufData:{},currentTimeMillis:{}", protobufData, System.currentTimeMillis());
            if (protobufData.getType().equals(ProtobufDataTypeEnum.HEART_BEAT.getCode())) {
                TransferChannel transferChannel = TransferFactory.getInstance().getChannel(ctx.channel());
                transferChannel.setHeartConnectTime(System.currentTimeMillis());
            } else if (protobufData.getType().equals(ProtobufDataTypeEnum.COMMON_MESSAGE.getCode())) {
                ChatRecord chatRecord = JSONObject.parseObject(protobufData.getContent(), ChatRecord.class);
                ClientChanel clientChanel = ClientChanelServer.clientChanelServerMap.get(chatRecord.getToUserId());
                ChannelFuture channelFuture = clientChanel.getCtx().writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(protobufData)));
                channelFuture.addListener((ChannelFutureListener) future -> {
                    log.info("回复消息发送成功...");
                });
            }
        }
    }

}
