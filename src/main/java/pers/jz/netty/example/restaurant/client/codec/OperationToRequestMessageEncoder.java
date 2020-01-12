package pers.jz.netty.example.restaurant.client.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import pers.jz.netty.example.restaurant.common.CommonRequestMessage;
import pers.jz.netty.example.restaurant.common.order.OrderOperation;

import java.util.List;
import java.util.Random;

/**
 * @author Jemmy Zhang on 2020/1/12.
 */
public class OperationToRequestMessageEncoder extends MessageToMessageEncoder<OrderOperation> {
    @Override
    protected void encode(ChannelHandlerContext ctx, OrderOperation operation, List<Object> out) throws Exception {
        CommonRequestMessage commonRequestMessage = new CommonRequestMessage(new Random().nextLong(), operation);
        out.add(commonRequestMessage);
    }
}
