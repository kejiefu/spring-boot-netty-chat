package com.mountain.im.connector.actor;

import io.netty.channel.Channel;

public interface ITransferActor {

    /**
     * 连接到指定服务器
     * 返回一个channel
     *
     * @return
     */
    Channel connectionToTransfer() throws InterruptedException;

}
