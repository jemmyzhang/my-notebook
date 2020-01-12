package pers.jz.netty.example.restaurant.client.codec;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author Jemmy Zhang on 2020/1/2.
 */
public class OrderFrameDecoder extends LengthFieldBasedFrameDecoder {

    public OrderFrameDecoder() {
        super(Integer.MAX_VALUE, 0, 2, 0, 2);
    }
}
