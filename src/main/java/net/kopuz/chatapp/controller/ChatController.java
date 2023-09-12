package net.kopuz.chatapp.controller;

import net.kopuz.chatapp.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/messaging")
    public Message sendMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/messaging")
    public Message newUser(@Payload Message message, SimpMessageHeaderAccessor accessor){
        accessor.getSessionAttributes().put("username", message.getAuthor());
        return message;
    }
}
