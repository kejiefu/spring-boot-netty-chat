package com.mountain.im.connector.util;

import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;

/**
 * @author kejiefu
 */
public class Netty4Utils {

    public static String getIp(ChannelHandlerContext ctx) {

        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();

        return address.getAddress().getHostAddress();
    }
}
