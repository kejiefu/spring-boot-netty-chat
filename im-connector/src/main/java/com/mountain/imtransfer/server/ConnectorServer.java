package com.mountain.imtransfer.server;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mountain.imtransfer.handler.client.NettyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 连接客户端的server
 * @author kejiefu
 * @Description TODO
 * @Date 2020/12/12 14:53
 * @Created by kejiefu
 */
@Component
public class ConnectorServer implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(ConnectorServer.class);

    @Value("${connector.port}")
    private Integer port;

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("ConnectorClientServer-pool-%d").build();

    private static ExecutorService executorService = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());


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
        logger.info("ConnectorServer.run ...");
        executorService.submit(() -> {
            //服务端要建立两个group，一个负责接收客户端的连接，一个负责处理数据传输
            //bossGroup就是parentGroup，是负责处理TCP/IP连接的，而workerGroup就是childGroup，是负责处理Channel（通道）的I/O事件。
            //连接处理group
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            //事件处理group
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                ServerBootstrap serverBootstrap = new ServerBootstrap();
                serverBootstrap.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class).
                        childHandler(new NettyServerInitializer());
                //保持连接数
                serverBootstrap.option(ChannelOption.SO_BACKLOG, 300);
                //保持连接
                serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
                //有数据立即发送
                serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
                // 链接服务器
                ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

                Channel channel = channelFuture.channel();
                logger.info("ConnectorClientServer 已经启动,端口:" + port + ".");
                //等待服务监听端口关闭,就是由于这里会将线程阻塞，导致无法发送信息，所以我这里开了线程
                channel.closeFuture().sync();
            } catch (Exception ex) {
                logger.error("ConnectorClientServer.run:", ex);
            } finally {
                logger.info("bossGroup.shutdownGracefully workerGroup.shutdownGracefully");
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        });
    }

}
