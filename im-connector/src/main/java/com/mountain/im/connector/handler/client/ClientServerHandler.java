package com.mountain.im.connector.handler.client;

import com.mountain.im.connector.model.protobuf.BaseMessageProto;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author kejiefu
 */
@Slf4j
public class ClientServerHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker webSocketServerHandshaker;

    /**
     * 管道处理上下文，便于服务器推送数据到客户端
     */
    private ChannelHandlerContext ctx;

    /**
     * websocket第一次链接触发
     *
     * @param ctx
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        log.info("ClientServerHandler.handlerAdded...");
    }

    /**
     * websocket断开连接触发
     *
     * @param ctx
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        log.info("ClientServerHandler.handlerRemoved...");
    }

    /**
     * 建立链接
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("ClientServerHandler.channelActive...");
    }

    /**
     * 链接断开
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("ClientServerHandler.channelInactive...");
    }

    /**
     * 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("ClientServerHandler.exceptionCaught...");
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        log.info("ClientServerHandler.channelReadComplete...");
        ctx.flush();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("ClientServerHandler.channelRead0...{}", msg);
        if (msg instanceof FullHttpRequest) {
            // 传统的HTTP接入
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } if (msg instanceof WebSocketFrame) {
            ctx.writeAndFlush("连接成功");
        }
        // 如果是BaseMessageProto就是普通socket发送的协议数据
        else if (msg instanceof BaseMessageProto.BaseMessage) {
            ctx.writeAndFlush("连接成功");
        }
        // 如果是String
        else if (msg instanceof String) {
            ctx.writeAndFlush("连接成功");
        }
    }


    /**
     * 处理Http请求，完成WebSocket握手<br/>
     * 注意：WebSocket连接第一次请求使用的是Http
     *
     * @param ctx
     * @param request
     * @throws Exception
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        // 如果HTTP解码失败，返回HTTP异常
        if (!request.decoderResult().isSuccess() || (!"websocket".equals(request.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        // 正常WebSocket的Http连接请求，构造握手响应返回
        WebSocketServerHandshakerFactory wsFactory =
                new WebSocketServerHandshakerFactory("ws://" + request.headers().get(HttpHeaderNames.HOST),
                        null, false);
        webSocketServerHandshaker = wsFactory.newHandshaker(request);
        // 无法处理的websocket版本
        if (webSocketServerHandshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            // 将GET, POST所有请求参数转换成Map对象
            Map<String, String> paramMap = new RequestParser(request).parse();
            log.info("paramMap:{}", paramMap);
            // 向客户端发送websocket握手,完成握手
            webSocketServerHandshaker.handshake(ctx.channel(), request);
            // 记录管道处理上下文，便于服务器推送数据到客户端
            this.ctx = ctx;
        }
    }

    /**
     * Http返回
     *
     * @param ctx
     * @param request
     * @param response
     */
    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response) {
        // 返回应答给客户端
        if (response.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
            HttpUtil.setContentLength(response, response.content().readableBytes());
        }

        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(response);
        if (!HttpUtil.isKeepAlive(request) || response.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

}
