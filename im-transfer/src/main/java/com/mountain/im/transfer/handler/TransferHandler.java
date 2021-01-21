package com.mountain.im.transfer.handler;

import com.google.protobuf.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

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
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        log.info("channelRead0:{}", msg);
        if (msg instanceof Message) {
            log.info("Message...");
        }
    }

}
