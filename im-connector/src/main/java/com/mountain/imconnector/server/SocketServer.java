package com.mountain.imconnector.server;

import com.mountain.imconnector.handler.NettyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2020/12/12 14:53
 * @Created by kejiefu
 */
@Component
public class SocketServer implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    @Value("${websocket.port}")
    private Integer port;

    /**
     * 通过group方法关联了两个线程组，NioEventLoopGroup是用来处理I/O操作的线程池，
     * 第一个称为“boss”，用来accept客户端连接，第二个称为“worker”，处理客户端数据的读写操作。当然你也可以只用一个NioEventLoopGroup同时来处理连接和读写
     * childHandler用来配置具体的数据处理方式 ，可以指定编解码器，处理数据的Handler
     * ChannelHandlerContext 通道处理器上下文。
     * 当ChannelHandler添加到ChannelPipeline时，每一个处理器都会分配一个上下文与之绑定，生死不离。上下文可以自身处理器与其他的处理器进行交互，
     * 因为上下文并不会改变处理器本身，所以上下文是安全的。
     *
     * @param var1
     */
    @Override
    public void run(ApplicationArguments var1) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new NettyServerInitializer());
            // 链接服务器
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            Channel channel = channelFuture.channel();
            logger.info("WebSocket 已经启动,端口:" + port + ".");
            //服务器同步连接断开时,这句代码才会往下执行
            channel.closeFuture().sync();
        } catch (Exception ex) {
            logger.error("run:", ex);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
