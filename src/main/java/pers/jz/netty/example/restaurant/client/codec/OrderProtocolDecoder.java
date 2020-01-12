package pers.jz.netty.example.restaurant.client.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import pers.jz.netty.example.restaurant.common.CommonResponseMessage;

import java.util.List;

/**
 * @author Jemmy Zhang on 2020/1/2.
 */
public class OrderProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        CommonResponseMessage commonResponseMessage = new CommonResponseMessage();
        commonResponseMessage.decode(buf);
        out.add(commonResponseMessage);
    }
}
