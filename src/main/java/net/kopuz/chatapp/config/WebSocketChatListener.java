package net.kopuz.chatapp.config;

import net.kopuz.chatapp.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Optional;

@Component
public class WebSocketChatListener {
    private final SimpMessageSendingOperations messageTemplate;

    public WebSocketChatListener(SimpMessageSendingOperations messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event){
        System.out.println("new websocket connection has attended");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null){
            Message message = new Message();
            message.setAuthor(username);
            message.setType("Leave");

            messageTemplate.convertAndSend("/topic/public", message);
        }
    }
}
