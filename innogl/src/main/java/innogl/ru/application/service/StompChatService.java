package innogl.ru.application.service;

import innogl.ru.application.dto.ChatMessage;
import innogl.ru.application.dto.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StompChatService {

    private static final String START_MESSAGE = "START";

    private final ChatSessionService chatSessionService;

    public void sendMessage(String userToken, ChatMessage chatMessage) {
        if (!chatSessionService.isMemberOfChatSessionWithId(userToken, chatMessage.getChatId())) {
            throw new IllegalArgumentException("You are not a member of the chat");
        }
        if (chatMessage.getType() == MessageType.SYSTEM) {
            throw new IllegalArgumentException("User cannot send system message");
        }
    }

    public void sendStartMessageToChat(UUID chatId, MessageChannel channel) {
        ChatMessage message = ChatMessage.builder()
                .id(UUID.randomUUID())
                .chatId(chatId)
                .type(MessageType.SYSTEM)
                .content(START_MESSAGE)
                .build();

        messagingTemplate(channel).convertAndSendToUser(
                message.getChatId().toString(),"/queue/messages",
                message);
    }

    private SimpMessagingTemplate messagingTemplate(MessageChannel channel) {
        return new SimpMessagingTemplate(channel);
    }
}
