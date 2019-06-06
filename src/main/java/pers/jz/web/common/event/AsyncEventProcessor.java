package pers.jz.web.common.event;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pers.jz.web.common.ThreadPool;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;

/**
 * 异步事件处理器
 * Created by Jemmy Zhang on 2019/6/4
 */
@Slf4j
@Service
public class AsyncEventProcessor implements EventProcessor {


    Executor threadPool = ThreadPool.ioBusy();

    Map<EventType, Set<EventListener>> listenerMap = new ConcurrentHashMap<>();

    @Override
    public void addListener(EventType eventType, EventListener eventListener) {
        log.debug("Add event listener. eventType={}, eventListener={}", eventType, eventListener);
        Set<EventListener> listeners = listenerMap.get(eventType);
        if (CollectionUtils.isEmpty(listeners)) {
            listeners = new CopyOnWriteArraySet<>();
        }
        listeners.add(eventListener);
        listenerMap.put(eventType, listeners);
    }

    @Override
    public <T> void publish(EventType eventType, RequestData<T> data) {
        log.debug("Receiver a publish, eventType ={}, data={}", eventType, data);
        Set<EventListener> eventListeners = listenerMap.get(eventType);
        if (Objects.isNull(eventListeners)) {
            log.debug("Publish to no one. No listeners typed {}", eventType);
        } else {
            eventListeners.stream().forEach((listener) -> threadPool.execute(() -> onEvent(data, listener)));
        }
    }

    private <T> void onEvent(RequestData<T> data, EventListener listener) {
        try {
            listener.onEvent(data);
        } catch (Exception e) {
            log.warn("Publish Event failed. reason: {}", e);
        }
    }
}
