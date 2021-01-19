package com.mountain.im.connector.factory;


import com.mountain.im.connector.constant.TransferConstant;
import com.mountain.im.connector.transfer.TransferChannel;
import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 连接transfer工厂类
 *
 * @author kejiefu
 */
@Slf4j
public class TransferFactory {

    private static final TransferFactory INSTANCE = new TransferFactory();

    public static TransferFactory getInstance() {
        return INSTANCE;
    }

    /**
     * im-connector与im-transfer的映射
     * 断线重连时需要重新关联
     * host:port-->TransferChannel
     */
    public final Map<String, TransferChannel> channelsMap = new ConcurrentHashMap<>();

    private TransferFactory() {

    }

    /**
     * 新创建一个通道
     * @param host host
     * @param port port
     * @return
     * @throws InterruptedException
     */
    public TransferChannel newChannel(String host, int port) throws InterruptedException {
        String serverId = String.format("%s:%s", host, port);
        TransferChannel transferChannel = this.channelsMap.get(serverId);
        if (transferChannel != null && !transferChannel.isDead()) {
            return transferChannel;
        }
        //通道如果不存在的话，就建立起关系
        transferChannel = new TransferChannel(host, port);
        this.channelsMap.put(serverId, transferChannel);
        return transferChannel;
    }

    public void removeChannel(String serverId) {
        this.channelsMap.remove(serverId);
    }

    /**
     * 关闭
     * @param channel
     */
    public void onClose(Channel channel) {
        HashMap<String, Object> context = (HashMap<String, Object>) channel.attr(TransferConstant.NETTY_CHANNEL_ATTR_KEY).get();

        if (context == null) {
            return;
        }

        String serverId = (String) context.get("serverId");

        TransferChannel transferChannel = this.channelsMap.get(serverId);

        if (transferChannel == null) {
            log.error("onClose未处理,已经被踢下线,serverId=" + serverId);
            return;
        }

        synchronized (transferChannel) {
            if (!transferChannel.isDead()) {
                log.info("IP:{}断开连接", transferChannel.getHost());
                this.removeChannel(serverId);
                transferChannel.setDead(true);
                transferChannel.disconnect();
            }
        }
    }


}
