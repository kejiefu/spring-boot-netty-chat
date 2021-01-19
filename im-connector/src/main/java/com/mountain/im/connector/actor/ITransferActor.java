package com.mountain.im.connector.actor;

import io.netty.channel.Channel;

public interface ITransferActor {

    /**
     * 连接通道
     * 返回一个channel
     *
     * @return
     */
    Channel connectionToTransfer() throws InterruptedException;

}
