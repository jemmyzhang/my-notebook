package pers.jz.netty.example.restaurant.server.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import pers.jz.netty.example.restaurant.common.CommonRequestMessage;
import pers.jz.netty.example.restaurant.common.CommonResponseMessage;

import java.util.List;

/**
 * @author Jemmy Zhang on 2020/1/2.
 */
public class OrderProtocolEncoder extends MessageToMessageEncoder<CommonResponseMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CommonResponseMessage responseMessage, List<Object> out) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();
        responseMessage.encode(buffer);
        out.add(buffer);
    }
}
