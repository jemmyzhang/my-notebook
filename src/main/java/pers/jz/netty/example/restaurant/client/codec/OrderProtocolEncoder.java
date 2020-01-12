package pers.jz.netty.example.restaurant.client.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import pers.jz.netty.example.restaurant.client.dispatcher.OperationResultFuture;
import pers.jz.netty.example.restaurant.client.dispatcher.RequestPendingCenter;
import pers.jz.netty.example.restaurant.common.CommonRequestMessage;

import java.util.List;

/**
 * @author Jemmy Zhang on 2020/1/2.
 */
public class OrderProtocolEncoder extends MessageToMessageEncoder<CommonRequestMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CommonRequestMessage requestMessage, List<Object> out) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();
        requestMessage.encode(buffer);
        out.add(buffer);
    }
}
