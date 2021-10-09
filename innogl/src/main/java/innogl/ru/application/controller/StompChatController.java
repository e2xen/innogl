package innogl.ru.application.controller;

import innogl.ru.application.dto.ChatMessage;
import innogl.ru.application.service.StompChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import static innogl.ru.application.constants.StompHeaders.AUTH_HEADER;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final StompChatService service;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send-message")
    public void sendMessage(@Header(AUTH_HEADER) String userToken, @Payload ChatMessage chatMessage) {
        log.info("sendMessage() - message = {}", chatMessage.toString());

        service.sendMessage(userToken, chatMessage);

        messagingTemplate.convertAndSendToUser(
                chatMessage.getChatId().toString(),"/queue/messages",
                chatMessage);
    }
}
