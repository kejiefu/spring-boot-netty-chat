package com.mountain.imtransfer.handler.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @Author: lsx
 * @Date: Create in 2020/5/22 16:07
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline().addLast("socketChoose", new ClientChooseHandler());
    }
}
