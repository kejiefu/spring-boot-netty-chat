package com.mountain.imconnector.handler.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SocketServerHandler extends SimpleChannelInboundHandler<Object> {
    

    /**
     * websocket第一次链接触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

    }

    /**
     * websocket断开连接触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

    }
    
    /**
	 * 建立链接
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

	}
	
	/**
	 * 链接断开
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

	}
	
	/**
	 * 异常
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {

	}
	
	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) {

    }

}
