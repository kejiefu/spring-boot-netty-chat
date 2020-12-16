package com.mountain.imconnector.handler.transfer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TransferHandler extends SimpleChannelInboundHandler<Object> {


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {


    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

}
