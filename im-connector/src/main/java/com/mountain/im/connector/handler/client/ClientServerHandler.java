package com.mountain.im.connector.handler.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientServerHandler extends SimpleChannelInboundHandler<Object> {


    /**
     * websocket第一次链接触发
     *
     * @param ctx
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {

    }

    /**
     * websocket断开连接触发
     *
     * @param ctx
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {

    }

    /**
     * 建立链接
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {

    }

    /**
     * 链接断开
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {

    }

    /**
     * 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) {

    }

}
