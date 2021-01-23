package com.mountain.im.connector.transfer;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.protobuf.ByteString;
import com.mountain.common.domain.ProtobufData;
import com.mountain.common.eums.ProtobufDataTypeEnum;
import com.mountain.im.connector.actor.ITransferActor;
import com.mountain.im.connector.actor.impl.TransferActor;
import com.mountain.im.connector.constant.HeartBeatConstant;
import com.mountain.im.connector.constant.TransferConstant;
import com.mountain.im.connector.model.protobuf.BaseMessageProto;
import io.netty.channel.Channel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.*;

/**
 * @author kejiefu
 */
@Data
@Slf4j
public class TransferChannel {

    /**
     * 网络对象
     */
    private Channel channel;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 端口
     */
    private int port;

    /**
     * ip
     */
    private String host;

    /**
     * 连接
     */
    private ITransferActor actor;

    /**
     * 是否无效了
     */
    private boolean isDead = false;

    /**
     * 默认最大并发数:cpu 核心 x 2<br>
     */
    private static final int CORE_POOL_SIZE = 1;

    /**
     * 心跳连接时间
     */
    public long heartConnectTime = System.currentTimeMillis();

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("ConnectorClientServer-pool-%d").build();

    /**
     * 定时任务线程
     */
    private final ScheduledExecutorService schedule = new ScheduledThreadPoolExecutor(CORE_POOL_SIZE, namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public TransferChannel(String host, int port) throws InterruptedException {
        this.port = port;
        this.host = host;
        this.createTime = System.currentTimeMillis();
        this.actor = new TransferActor(port, host);
        this.channel = this.actor.connectionToTransfer();
        if (this.channel != null) {
            //每隔一段时间发送一次心跳数据
            this.schedule.scheduleWithFixedDelay(() -> {
                try {
                    //组装心跳内容
                    BaseMessageProto.BaseMessage.Builder builder = BaseMessageProto.BaseMessage.newBuilder();
                    ProtobufData protobufData = new ProtobufData();
                    protobufData.setType(ProtobufDataTypeEnum.HEART_BEAT.getCode());
                    protobufData.setContent("心跳");
                    protobufData.setTime(System.currentTimeMillis());
                    String jsonString = JSONObject.toJSONString(protobufData);
                    ByteString bytes = ByteString.copyFrom(jsonString, "UTF-8");
                    builder.setData(bytes);
                    BaseMessageProto.BaseMessage message = builder.build();
                    log.info("发送心跳到transfer,{}", jsonString);
                    this.channel.writeAndFlush(message);
                } catch (Exception ex) {
                    log.error("scheduleWithFixedDelay:", ex);
                }
            }, 1000, HeartBeatConstant.HEART_BEAT_CHECK, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 心跳是否已经停止
     */
    public boolean isHeatBeatStop(long now) {
        return (now - this.heartConnectTime) > HeartBeatConstant.HEART_BEAT_CHECK;
    }

    /**
     * 踢下线
     */
    public void kickOut() {
        channel.close();
    }


    public Object getContextParam(String key) {
        HashMap<String, Object> context = (HashMap<String, Object>) channel.attr(TransferConstant.NETTY_CHANNEL_ATTR_KEY).get();
        if (context == null) {
            context = new HashMap<>(16);
            channel.attr(TransferConstant.NETTY_CHANNEL_ATTR_KEY).set(context);
        }
        return context.get(key);
    }

    public void setContextParam(String key, Object value) {
        HashMap<String, Object> context = (HashMap<String, Object>) channel.attr(TransferConstant.NETTY_CHANNEL_ATTR_KEY).get();
        if (context == null) {
            context = new HashMap<>(16);
            context.put(key, value);
            channel.attr(TransferConstant.NETTY_CHANNEL_ATTR_KEY).set(context);
        }
    }

    public void disconnect() {
        if (this.channel != null) {
            this.channel.disconnect();
            this.schedule.shutdown();
            this.channel = null;
        }
    }


}
