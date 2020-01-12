package pers.jz.netty.example.restaurant.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import pers.jz.netty.example.restaurant.client.codec.*;
import pers.jz.netty.example.restaurant.client.dispatcher.OperationResultFuture;
import pers.jz.netty.example.restaurant.client.dispatcher.RequestPendingCenter;
import pers.jz.netty.example.restaurant.client.handler.OperationResultHandler;
import pers.jz.netty.example.restaurant.common.CommonRequestMessage;
import pers.jz.netty.example.restaurant.common.Operation;
import pers.jz.netty.example.restaurant.common.order.OrderOperation;

import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * @author Jemmy Zhang on 2020/1/12.
 */
@Slf4j
public class RestaurantClient {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(new NioEventLoopGroup());
        RequestPendingCenter center = new RequestPendingCenter();
        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new OrderFrameDecoder());
                pipeline.addLast(new OrderFrameEncoder());
                pipeline.addLast(new OrderProtocolDecoder());
                pipeline.addLast(new OrderProtocolEncoder());
                pipeline.addLast(new OperationResultHandler(center));
                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                pipeline.addLast(new OperationToRequestMessageEncoder());
            }
        });
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8800).sync();

        Operation meat = new OrderOperation(2, "Meat");
        channelFuture.channel().writeAndFlush(meat);
        OperationResultFuture resultFuture = new OperationResultFuture();

        CommonRequestMessage requestMessage = new CommonRequestMessage(new Random().nextLong(), new OrderOperation(1, "Fish"));
        channelFuture.channel().writeAndFlush(requestMessage);
        center.add(requestMessage.getMessageHeader().getStreamId(), resultFuture);
        log.info("============>Result is: {}", resultFuture.get());

        channelFuture.channel().closeFuture().get();
    }
}
