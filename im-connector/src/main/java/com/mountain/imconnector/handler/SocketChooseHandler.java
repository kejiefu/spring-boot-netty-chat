package com.mountain.imconnector.handler;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 处理客户端的消息
 *
 * @author kejiefu
 * @Description TODO
 * @Date 2020/12/12 15:00
 * @Created by kejiefu
 */
public class SocketChooseHandler extends ByteToMessageDecoder {

    /**
     * WebSocket握手的协议前缀
     */
    private static final String WEBSOCKET_PREFIX = "GET /";

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String protocol = getBufStart(in);
        if (protocol.startsWith(WEBSOCKET_PREFIX)) {
            //  websocket连接时，执行以下处理
            // HttpServerCodec：将请求和应答消息解码为HTTP消息
            ctx.pipeline().addLast("http-codec", new HttpServerCodec());

            // HttpObjectAggregator：将HTTP消息的多个部分合成一条完整的HTTP消息
            ctx.pipeline().addLast("aggregator", new HttpObjectAggregator(65535));

            // ChunkedWriteHandler：向客户端发送HTML5文件,文件过大会将内存撑爆
            ctx.pipeline().addLast("http-chunked", new ChunkedWriteHandler());

            ctx.pipeline().addLast("ProtocolHandler", new WebSocketServerProtocolHandler("/ws", null, true, 65535));

            ctx.pipeline().addLast("socketHandler", null);
        } else {
            //  常规TCP连接时，执行以下处理

            //LengthFieldBasedFrameDecoder：TCP的解码器，可以靠它轻松搞定TCP粘包问题。
            ctx.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
            //构造函数传递要解码成的类型
            //可以通过：protobuf方式进行解码和编码，以提高网络消息的传输效率。
            ctx.pipeline().addLast("protobufDecoder", null);

            //编码用
            ctx.pipeline().addLast("frameEncoder", new LengthFieldPrepender(4, false));
            ctx.pipeline().addLast("protobufEncoder", new ProtobufEncoder());
            ctx.pipeline().addLast("readTimeOut", new ReadTimeoutHandler(1200, TimeUnit.SECONDS));
            ctx.pipeline().addLast("handler", null);
        }
        in.resetReaderIndex();
        ctx.pipeline().remove(this.getClass());
    }

    private String getBufStart(ByteBuf in) {
        int length = in.readableBytes();
        // 标记读位置
        in.markReaderIndex();
        byte[] content = new byte[length];
        in.readBytes(content);
        return new String(content);
    }
}
