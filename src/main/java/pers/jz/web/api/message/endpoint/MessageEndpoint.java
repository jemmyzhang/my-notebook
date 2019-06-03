package pers.jz.web.api.message.endpoint;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jemmy Zhang
 */

@Slf4j
@Data
@Service
@ServerEndpoint("/message/{username}")
public class MessageEndpoint {
    private static final AtomicInteger onlineCount = new AtomicInteger(0);
    private static final Map<String, MessageEndpoint> clients = new ConcurrentHashMap<String, MessageEndpoint>();
    private Session session;
    private String username;

    @OnOpen
    public void onOpen(@PathParam("username") String userName, Session session) throws IOException {
        this.username = userName;
        this.session = session;
        addOnlineCount();
        clients.put(username, this);
        log.debug("Connect to server success. username:{} ", username);
    }

    @OnClose
    public void onClose() {
        clients.remove(username);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        String id = session.getId();
        sendMessage(session, "Message Received: " + message);
    }

    public static MessageEndpoint getEndpoint(String key) {
        return clients.get(key);
    }

    public void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    private void addOnlineCount() {
        onlineCount.incrementAndGet();
    }

    private void subOnlineCount() {
        onlineCount.decrementAndGet();
    }
}
