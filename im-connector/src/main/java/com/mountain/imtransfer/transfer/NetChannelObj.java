package com.mountain.imtransfer.transfer;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mountain.imtransfer.transfer.actor.IConnectionActor;
import com.mountain.imtransfer.transfer.actor.impl.ConnectionActor;
import com.mountain.imtransfer.transfer.constant.ConnectionConstant;
import io.netty.channel.Channel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.*;

@Data
@Slf4j
public class NetChannelObj {

    /**
     * 创建时间
     */
    private long createTime;

    private int port;

    private String host;

    private IConnectionActor actor;

    /**
     * 是否无效了
     */
    private boolean isDead = false;

    /**
     * 默认最大并发数:cpu 核心 x 2<br>
     */
    private static final int CORE_POOL_SIZE = 1;

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("ConnectorClientServer-pool-%d").build();

    /**
     * 定时任务线程
     */
    private final ScheduledExecutorService schedule = new ScheduledThreadPoolExecutor(CORE_POOL_SIZE, namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    /**
     * 网络对象
     */
    private Channel channel;

    public NetChannelObj(String host, int port) throws InterruptedException {
        this.port = port;
        this.host = host;
        this.createTime = System.currentTimeMillis();
        this.actor = new ConnectionActor(port, host);
        this.channel = this.actor.connectionToServer();
        if (this.channel != null) {
            //每隔30分钟发送一次心跳数据
            this.schedule.scheduleWithFixedDelay(() -> {

            }, 1, 1800, TimeUnit.SECONDS);
        }
    }

    /**
     * 踢下线
     */
    public void kickOut() {
        channel.close();
    }


    /**
     * 发送socket的响应
     */
    public void pushBytes() {
        channel.writeAndFlush(null);
    }

    public Object getContextParam(String key) {
        HashMap<String, Object> context = (HashMap<String, Object>) channel.attr(ConnectionConstant.NETTY_CHANNEL_ATTR_KEY).get();
        if (context == null) {
            context = new HashMap<>(16);
            channel.attr(ConnectionConstant.NETTY_CHANNEL_ATTR_KEY).set(context);
        }
        return context.get(key);
    }

    public void setContextParam(String key, Object value) {
        HashMap<String, Object> context = (HashMap<String, Object>) channel.attr(ConnectionConstant.NETTY_CHANNEL_ATTR_KEY).get();
        if (context == null) {
            context = new HashMap<>(16);
            channel.attr(ConnectionConstant.NETTY_CHANNEL_ATTR_KEY).set(context);
        }
        context.put(key, value);
    }


    public void disconnect() {
        if (this.channel != null) {
            log.info("连接已经断开,serverId=" + this.getContextParam("serverId"));
            this.channel.disconnect();
            this.schedule.shutdown();
            this.channel = null;
        }
    }

}
