package com.mountain.imconnector.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @Author: lsx
 * @Date: Create in 2020/5/22 16:07
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("socketChoose", new SocketChooseHandler());
    }
}
