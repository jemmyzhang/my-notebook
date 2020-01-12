package pers.jz.netty.example.restaurant.common;

import lombok.Getter;
import pers.jz.netty.example.restaurant.common.auth.AuthOperation;
import pers.jz.netty.example.restaurant.common.auth.AuthOperationResult;
import pers.jz.netty.example.restaurant.common.keepalive.KeepAliveOperation;
import pers.jz.netty.example.restaurant.common.keepalive.KeepAliveOperationResult;
import pers.jz.netty.example.restaurant.common.order.OrderOperation;
import pers.jz.netty.example.restaurant.common.order.OrderOperationResult;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 操作类型枚举
 *
 * @author Jemmy Zhang on 2020/1/1.
 */
@Getter
public enum OperationType {

    AUTH(1, AuthOperation.class, AuthOperationResult.class),
    KEEP_ALIVE(2, KeepAliveOperation.class, KeepAliveOperationResult.class),
    ORDER(3, OrderOperation.class, OrderOperationResult.class);

    private int opCode;
    private Class<? extends Operation> operationClazz;
    private Class<? extends OperationResult> operationResultClazz;

    OperationType(int opCode, Class<? extends Operation> operationClazz, Class<? extends OperationResult> operationResultClazz) {
        this.opCode = opCode;
        this.operationClazz = operationClazz;
        this.operationResultClazz = operationResultClazz;
    }

    public static OperationType fromOpCode(int type) {
        return getOperationType(requestType -> requestType.opCode == type);
    }

    public static OperationType fromOperation(Operation operation) {
        return getOperationType(requestType -> requestType.operationClazz == operation.getClass());
    }

    private static OperationType getOperationType(Predicate<OperationType> predicate) {
        OperationType[] values = values();
        return Stream.of(values).filter((value) -> predicate.test(value)).findAny().orElseThrow(() -> new AssertionError("Not found type"));
    }
}
