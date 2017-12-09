package cn.campus.platfrom.handler;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/websocket")
public class WebsocketHandler {

    @RequestMapping("/test")
    public String test(){
        return "socket/test";
    }

    @MessageMapping("/topic")
    @SendTo("/topic/message")
    public String executeTopic(String message) throws Exception {
        return message;
    }

    @MessageMapping("/trade")
    @SendToUser("/topic/user")
    public String executeTrade(String message) throws Exception {
        return message;
    }
}
