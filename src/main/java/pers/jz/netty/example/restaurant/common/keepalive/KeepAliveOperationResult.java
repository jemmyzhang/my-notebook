package pers.jz.netty.example.restaurant.common.keepalive;

import lombok.Data;
import pers.jz.netty.example.restaurant.common.OperationResult;

/**
 * @author Jemmy Zhang on 2020/1/1.
 */
@Data
public class KeepAliveOperationResult extends OperationResult {
    private final long time;
}
