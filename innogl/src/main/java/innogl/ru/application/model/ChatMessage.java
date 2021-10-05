package innogl.ru.application.model;

import lombok.Data;

import java.util.UUID;

@Data
public class ChatMessage {
    private UUID id;
    private UUID chatId;
    String senderId;
    String recipientId;
    String content;
}
