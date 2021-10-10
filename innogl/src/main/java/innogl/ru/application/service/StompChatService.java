package innogl.ru.application.service;

import innogl.ru.application.dto.ChatMessage;
import innogl.ru.application.dto.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StompChatService {

    private static final String START_MESSAGE = Base64.getEncoder().encodeToString("START".getBytes());

    @Lazy
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private final ChatSessionService chatSessionService;

    public void sendMessage(String userToken, ChatMessage chatMessage) {
        if (!chatSessionService.isMemberOfChatSessionWithId(userToken, chatMessage.getChatId())) {
            throw new IllegalArgumentException("You are not a member of the chat");
        }
        if (chatMessage.getType() == MessageType.SYSTEM) {
            throw new IllegalArgumentException("User cannot send system message");
        }

        messagingTemplate.convertAndSendToUser(
                chatMessage.getChatId().toString(),"/queue/messages",
                chatMessage);
    }

    public void sendStartMessageToChat(UUID chatId) {
        if (chatSessionService.isChatFull(chatId)) {
            ChatMessage message = ChatMessage.builder()
                    .id(UUID.randomUUID())
                    .chatId(chatId)
                    .type(MessageType.SYSTEM)
                    .content(START_MESSAGE)
                    .build();

            messagingTemplate.convertAndSendToUser(
                    message.getChatId().toString(),"/queue/messages",
                    message);
        }
    }
}
