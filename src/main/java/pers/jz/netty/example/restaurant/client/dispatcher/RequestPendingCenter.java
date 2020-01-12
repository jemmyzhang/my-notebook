package pers.jz.netty.example.restaurant.client.dispatcher;

import pers.jz.netty.example.restaurant.common.OperationResult;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jemmy Zhang on 2020/1/12.
 */
public class RequestPendingCenter {

    private Map<Long, OperationResultFuture> container = new ConcurrentHashMap<>();

    public void add(Long streamId, OperationResultFuture operationResultFuture) {
        container.put(streamId, operationResultFuture);
    }

    public void setResult(Long streamId, OperationResult operationResult) {
        OperationResultFuture resultFuture = container.get(streamId);
        if (Objects.nonNull(resultFuture)) {
            resultFuture.setSuccess(operationResult);
            container.remove(streamId);
        }
    }
}
