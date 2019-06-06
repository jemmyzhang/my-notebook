package pers.jz.web.common.event;

/**
 * @author Jemmy Zhang on 2019/6/6.
 */
@FunctionalInterface
public interface EventListener {

    void onEvent(RequestData requestData);
}
