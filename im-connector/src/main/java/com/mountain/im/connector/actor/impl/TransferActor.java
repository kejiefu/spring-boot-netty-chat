package com.mountain.im.connector.actor.impl;


import com.mountain.im.connector.actor.ITransferActor;
import com.mountain.im.connector.handler.transfer.TransferHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;


@Slf4j
public class TransferActor implements ITransferActor {

    private final int port;
    private final String host;

    public TransferActor(int port, String host) {
        this.port = port;
        this.host = host;
    }

    /**
     * 连接通道
     * @return
     * @throws InterruptedException
     */
    @Override
    public Channel connectionToTransfer() throws InterruptedException {
        Bootstrap bossGroup = new Bootstrap();

        EventLoopGroup group = new NioEventLoopGroup();
        //绑定客户端通道
        bossGroup.channel(NioSocketChannel.class);
        bossGroup.option(ChannelOption.SO_KEEPALIVE, true).option(ChannelOption.TCP_NODELAY, true);
        bossGroup.group(group);
        bossGroup.remoteAddress(host, port);
        //NioSocketChannel初始化handler，处理读写事件
        //通道是NioSocketChannel
        bossGroup.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) {
                //LengthFieldBasedFrameDecoder：TCP的解码器，可以靠它轻松搞定TCP粘包问题。
                ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                //构造函数传递要解码成的类型
                //可以通过：protobuf方式进行解码和编码，以提高网络消息的传输效率。
                //ch.pipeline().addLast("protobufDecoder", null);
                //编码用
                ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(4, false));
                ch.pipeline().addLast("protobufEncoder", new ProtobufEncoder());
                ch.pipeline().addLast("readTimeOut", new ReadTimeoutHandler(1200, TimeUnit.SECONDS));
                //转发信息的处理
                ch.pipeline().addLast("handler", new TransferHandler());
            }
        });
        //连接服务器
        ChannelFuture channelFuture = bossGroup.connect(host, port).sync();
        channelFuture.addListener((ChannelFutureListener) arg0 -> {
            if (channelFuture.isSuccess()) {
                log.info("netty client connection is successful ........................................");
            } else {
                log.info("netty client connection is failed ........................................");
                channelFuture.cause().printStackTrace();
            }
        });

        return channelFuture.channel();
    }
}
