package innogl.ru.application.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ChatMessage {
    private UUID id;
    private UUID chatId;
    private UUID senderId;
    private MessageType type;
    private String content;
}
