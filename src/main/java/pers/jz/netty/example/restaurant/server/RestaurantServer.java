package pers.jz.netty.example.restaurant.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import pers.jz.netty.example.restaurant.server.codec.OrderFrameDecoder;
import pers.jz.netty.example.restaurant.server.codec.OrderFrameEncoder;
import pers.jz.netty.example.restaurant.server.codec.OrderProtocolDecoder;
import pers.jz.netty.example.restaurant.server.codec.OrderProtocolEncoder;
import pers.jz.netty.example.restaurant.server.handler.OrderServerProcessHandler;

import java.util.concurrent.ExecutionException;

/**
 * @author Jemmy Zhang on 2020/1/3.
 */
public class RestaurantServer {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
        serverBootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup());
        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new OrderFrameDecoder());
                pipeline.addLast(new OrderFrameEncoder());
                pipeline.addLast(new OrderProtocolDecoder());
                pipeline.addLast(new OrderProtocolEncoder());
                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                pipeline.addLast(new OrderServerProcessHandler());

            }
        });
        ChannelFuture channelFuture = serverBootstrap.bind(8800).sync();
        channelFuture.channel().closeFuture().get();
    }
}
