package com.mountain.im.connector.handler.client;

import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * @author kejiefu
 * @Description TODO
 * @Date 2021/8/2 21:21
 * @Created by kejiefu
 */
public class ClientChanel {

    private String token;

    private ChannelHandlerContext ctx;

    private Date date;

    public ClientChanel(String token, ChannelHandlerContext ctx, Date date) {
        this.token = token;
        this.ctx = ctx;
        this.date = date;
    }

}
