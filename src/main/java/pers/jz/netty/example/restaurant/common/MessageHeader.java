package pers.jz.netty.example.restaurant.common;

import lombok.Data;

/**
 * @author Jemmy Zhang on 2020/1/1.
 */
@Data
public class MessageHeader {
    private int version = 1;
    private int opCode;
    private long streamId;
}
