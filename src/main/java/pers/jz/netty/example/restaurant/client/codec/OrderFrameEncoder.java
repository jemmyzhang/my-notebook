package pers.jz.netty.example.restaurant.client.codec;

import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @author Jemmy Zhang on 2020/1/2.
 */
public class OrderFrameEncoder extends LengthFieldPrepender {

    public OrderFrameEncoder() {
        super(2);
    }
}
