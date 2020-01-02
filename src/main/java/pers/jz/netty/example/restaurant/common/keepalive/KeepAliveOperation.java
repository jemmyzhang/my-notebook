package pers.jz.netty.example.restaurant.common.keepalive;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pers.jz.netty.example.restaurant.common.Operation;
import pers.jz.netty.example.restaurant.common.OperationResult;

/**
 * @author Jemmy Zhang on 2020/1/1.
 */

@Data
@Slf4j
public class KeepAliveOperation extends Operation {
    private long time;

    public KeepAliveOperation() {
        this.time = System.currentTimeMillis();
    }

    @Override
    public OperationResult execute() {
        KeepAliveOperationResult keepResponse =new KeepAliveOperationResult(time);
        return keepResponse;
    }
}
