package com.mountain.imtransfer.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kejiefu
 */
@Slf4j
public class TransferHandler extends SimpleChannelInboundHandler<Object> {


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx)  {


    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {

    }

}
