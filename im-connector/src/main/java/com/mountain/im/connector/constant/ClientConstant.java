package com.mountain.im.connector.constant;

import io.netty.util.AttributeKey;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/7/30 11:01
 * @Created by kejiefu
 */
public class ClientConstant {

    /**
     * netty ChannelHandlerContext.attr()的key定义
     */
    public static AttributeKey<Object> NETTY_CHANNEL_ATTR_KEY = AttributeKey.valueOf("NETTY_CHANNEL_ATTR_KEY");

}
