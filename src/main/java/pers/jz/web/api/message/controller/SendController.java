package pers.jz.web.api.message.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.jz.web.api.message.endpoint.MessageEndpoint;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Jemmy Zhang on 2019/6/4.
 */
@RestController
@RequestMapping(value = "/send")
public class SendController {

    @GetMapping("/{user}/{message}")
    public void send(@PathVariable(value = "user") String user, @PathVariable(value = "message") String message) throws IOException {
        MessageEndpoint messageEndpoint = MessageEndpoint.getEndpoint(user);
        if (Objects.nonNull(messageEndpoint) && Objects.nonNull(messageEndpoint.getSession())) {
            messageEndpoint.sendMessage(messageEndpoint.getSession(), message);
        }

    }

}
