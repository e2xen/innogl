package innogl.ru.application.controller;

import innogl.ru.application.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send-message")
    public void sendMessage(@Payload ChatMessage chatMessage) {

        messagingTemplate.convertAndSendToUser(
                chatMessage.getChatId().toString(),"/queue/messages",
                chatMessage);
    }
}
