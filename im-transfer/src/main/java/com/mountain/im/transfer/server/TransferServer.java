package com.mountain.im.transfer.server;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mountain.im.transfer.handler.TransferHandler;
import com.mountain.im.transfer.model.protobuf.BaseMessageProto;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.concurrent.*;

/**
 * TransferServer
 *
 * @author kejiefu
 * @Description TODO
 * @Date 2020/12/12 14:53
 * @Created by kejiefu
 */
@Component
public class TransferServer implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(TransferServer.class);

    @Value("${transfer.port}")
    private Integer transferPort;

    @Value("${transfer.name}")
    private String transferName;

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serverAddress;

    private static final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("ConnectorClientServer-pool-%d").build();

    private static final ExecutorService executorService = new ThreadPoolExecutor(1, 1,
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
        logger.info("TransferServer.run ...");
        executorService.submit(() -> {
            //服务端要建立两个group，一个负责接收客户端的连接，一个负责处理数据传输
            //bossGroup就是parentGroup，是负责处理TCP/IP连接的，而workerGroup就是childGroup，是负责处理Channel（通道）的I/O事件。
            //连接处理group
            //使用 NioEventLoopGroup 类的无参构造函数设置线程数量的默认值就是 CPU 核心数 *2 。
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            //事件处理group
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                ServerBootstrap serverBootstrap = new ServerBootstrap();
                serverBootstrap.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel channel) throws Exception {
                                ChannelPipeline pipeline = channel.pipeline();
                                //ReadTimeoutHandler 如果在设置时间段内都没有数据读取了，那么就引发超时，然后关闭当前的channel
                                pipeline.addLast("readTimeOut", new ReadTimeoutHandler(120, TimeUnit.SECONDS));
                                //解码
                                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                                pipeline.addLast("protobufDecoder", new ProtobufDecoder(BaseMessageProto.BaseMessage.getDefaultInstance()));
                                //编码
                                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4, false));
                                pipeline.addLast("protobufEncoder", new ProtobufEncoder());
                                //处理
                                pipeline.addLast("handler", new TransferHandler());
                            }
                        });
                //保持连接数
                serverBootstrap.option(ChannelOption.SO_BACKLOG, 300);
                //保持连接
                serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
                //有数据立即发送
                serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
                // 链接服务器
                ChannelFuture channelFuture = serverBootstrap.bind(transferPort).sync();

                Channel channel = channelFuture.channel();
                logger.info("TransferServer 已经启动,端口:" + transferPort + ".");

                //netty服务注入到nacos
                registerService();

                //等待服务监听端口关闭,就是由于这里会将线程阻塞，导致无法发送信息，所以我这里开了线程
                channel.closeFuture().sync();
            } catch (Exception ex) {
                logger.error("TransferServer.run:", ex);
            } finally {
                logger.info("bossGroup.shutdownGracefully   workerGroup.shutdownGracefully");
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        });
    }

    /**
     * 注册netty的信息到nacos
     *
     * @throws Exception
     */
    private void registerService() throws Exception {
        //获取nacos服务
        NamingService namingService = NamingFactory.createNamingService(serverAddress);
        InetAddress address = InetAddress.getLocalHost();
        //服务地址的ip
        String ip = address.getHostAddress();
        //注册
        namingService.registerInstance(transferName, ip, transferPort);
    }


}
