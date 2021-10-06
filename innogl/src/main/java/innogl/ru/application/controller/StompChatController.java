package innogl.ru.application.controller;

import innogl.ru.application.model.ChatMessage;
import innogl.ru.application.service.ChatSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatSessionService chatSessionService;

    @MessageMapping("/send-message")
    public void sendMessage(@Payload ChatMessage chatMessage) throws IllegalAccessException {
        log.info("sendMessage() - message = {}", chatMessage.toString());
        if (!chatSessionService.isMemberOfChatSessionWithId(chatMessage.getSenderId(), chatMessage.getChatId())) {
            throw new IllegalAccessException("You are not a member of the chat");
        }


        messagingTemplate.convertAndSendToUser(
                chatMessage.getChatId().toString(),"/queue/messages",
                chatMessage);
    }
}
