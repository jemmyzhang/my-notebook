package pers.jz.netty.example.restaurant.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import pers.jz.netty.example.restaurant.client.dispatcher.RequestPendingCenter;
import pers.jz.netty.example.restaurant.common.CommonResponseMessage;

/**
 * @author Jemmy Zhang on 2020/1/12.
 */
public class OperationResultHandler extends ChannelInboundHandlerAdapter {

    RequestPendingCenter requestPendingCenter;

    public OperationResultHandler(RequestPendingCenter requestPendingCenter) {
        this.requestPendingCenter = requestPendingCenter;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof CommonResponseMessage) {
            CommonResponseMessage responseMessage = (CommonResponseMessage) msg;
            long streamId = responseMessage.getMessageHeader().getStreamId();
            requestPendingCenter.setResult(streamId, responseMessage.getMessageBody());
        }
    }
}
