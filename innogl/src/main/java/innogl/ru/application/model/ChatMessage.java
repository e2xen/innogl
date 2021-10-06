package innogl.ru.application.model;

import lombok.Data;

import java.util.UUID;

@Data
public class ChatMessage {
    private UUID id;
    private UUID chatId;
    UUID senderId;
    String content;
}
