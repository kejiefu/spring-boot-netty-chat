package com.mountain.imtransfer.transfer;


import com.mountain.imtransfer.transfer.constant.ConnectionConstant;
import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class ConnectionFactory {

    private static final ConnectionFactory INSTANCE = new ConnectionFactory();

    private ConnectionFactory() {

    }

    public static ConnectionFactory getInstance() {
        return INSTANCE;
    }

    /**
     * im-connector与im-transfer的映射
     * 断线重连时需要重新关联
     * host:port-->NetChannelObj
     */
    private final Map<String, NetChannelObj> channelsMap = new ConcurrentHashMap<>();

    public NetChannelObj newChannel(int port, String host) throws InterruptedException {

        String serverId = String.format("%s:%s", host, port);

        NetChannelObj netChannelObj = this.channelsMap.get(serverId);

        if (netChannelObj != null && !netChannelObj.isDead()) {
            return netChannelObj;
        }

        netChannelObj = new NetChannelObj(host, port);

        this.channelsMap.put(serverId, netChannelObj);

        return netChannelObj;
    }

    public void removeChannel(String serverId) {
        this.channelsMap.remove(serverId);
    }

    public void onClose(Channel channel) {
        HashMap<String, Object> context = (HashMap<String, Object>) channel.attr(ConnectionConstant.NETTY_CHANNEL_ATTR_KEY).get();

        if (context == null) {
            return;
        }

        String serverId = (String) context.get("serverId");

        NetChannelObj channelObj = this.channelsMap.get(serverId);

        if (channelObj == null) {
            log.error("onClose未处理,已经被踢下线,serverId=" + serverId);
            return;
        }

        synchronized (channelObj) {

            if (!channelObj.isDead()) {
                log.info("IP:{}断开连接", channelObj.getHost());
                this.removeChannel(serverId);
                channelObj.setDead(true);
                channelObj.disconnect();
            }
        }
    }


}
