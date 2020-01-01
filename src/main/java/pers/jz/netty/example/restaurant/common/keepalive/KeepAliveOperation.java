package pers.jz.netty.example.restaurant.common.keepalive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pers.jz.netty.example.restaurant.common.AbstractOperation;
import pers.jz.netty.example.restaurant.common.AbstractOperationResult;

/**
 * @author Jemmy Zhang on 2020/1/1.
 */

@Data
@Slf4j
public class KeepAliveOperation extends AbstractOperation {
    private long time;

    public KeepAliveOperation() {
        this.time = System.currentTimeMillis();
    }

    @Override
    public AbstractOperationResult execute() {
        KeepAliveOperationResult keepResponse =new KeepAliveOperationResult(time);
        return keepResponse;
    }
}
