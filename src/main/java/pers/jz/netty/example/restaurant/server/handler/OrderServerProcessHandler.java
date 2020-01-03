package pers.jz.netty.example.restaurant.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import pers.jz.netty.example.restaurant.common.CommonRequestMessage;
import pers.jz.netty.example.restaurant.common.CommonResponseMessage;
import pers.jz.netty.example.restaurant.common.Operation;
import pers.jz.netty.example.restaurant.common.OperationResult;

/**
 * @author Jemmy Zhang on 2020/1/2.
 */
public class OrderServerProcessHandler extends SimpleChannelInboundHandler<CommonRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CommonRequestMessage requestMessage) throws Exception {
        Operation operation = requestMessage.getMessageBody();
        OperationResult operationResult = operation.execute();

        CommonResponseMessage responseMessage = new CommonResponseMessage();
        responseMessage.setMessageHeader(requestMessage.getMessageHeader());
        responseMessage.setMessageBody(operationResult);

        ctx.writeAndFlush(responseMessage);
    }
}
