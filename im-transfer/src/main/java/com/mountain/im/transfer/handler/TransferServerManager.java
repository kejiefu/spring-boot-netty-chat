package com.mountain.im.transfer.handler;

import com.google.common.base.Strings;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/10/8 14:49
 * @Created by kejiefu
 */
@Slf4j
public class TransferServerManager {

    public static final Map<String, ChannelHandlerContext> channelHandlerContextMap = new ConcurrentHashMap<>();

    public static boolean register(String address, ChannelHandlerContext ctx) {
        if (Strings.isNullOrEmpty(address) || channelHandlerContextMap.containsKey(address)) {
            return false;
        }
        channelHandlerContextMap.put(address, ctx);
        return true;
    }

    public static boolean logout(String address) {
        if (Strings.isNullOrEmpty(address) || !channelHandlerContextMap.containsKey(address)) {
            return false;
        }
        channelHandlerContextMap.remove(address);
        return true;
    }

}
