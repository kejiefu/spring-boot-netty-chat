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

    private Long userId;

    private ChannelHandlerContext ctx;

    private Date date;

    public ClientChanel(Long userId, ChannelHandlerContext ctx, Date date) {
        this.userId = userId;
        this.ctx = ctx;
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }
}
