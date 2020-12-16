package com.mountain.imconnector.transfer.constant;

import io.netty.util.AttributeKey;

public class ConnectionConstant {
    
    /**
     * netty ChannelHandlerContext.attr()的key定义
     */
    public static AttributeKey<Object> NETTY_CHANNEL_ATTR_KEY = AttributeKey.valueOf("NETTY_CHANNEL_ATTR_KEY");

}
