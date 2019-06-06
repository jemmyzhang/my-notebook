package pers.jz.web.common.event;

/**
 * 事件处理器
 *
 * @author Jemmy Zhang on 2019/6/6.
 */
public interface EventProcessor {

    /**
     * 添加事件监听者
     *
     * @param eventType     事件类型
     * @param eventListener 事件监听者
     */
    void addListener(EventType eventType, EventListener eventListener);

    /**
     * 发布事件
     *
     * @param eventType 事件类型
     * @param data      事件数据
     * @param <T>
     */
    <T> void publish(EventType eventType, RequestData<T> data);
}
