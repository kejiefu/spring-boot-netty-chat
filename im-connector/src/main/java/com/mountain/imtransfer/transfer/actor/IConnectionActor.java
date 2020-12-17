package com.mountain.imtransfer.transfer.actor;

import io.netty.channel.Channel;

public interface IConnectionActor {

    /**
     * 连接到指定服务器
     * 返回一个channel
     *
     * @return
     */
    Channel connectionToServer() throws InterruptedException;

}
