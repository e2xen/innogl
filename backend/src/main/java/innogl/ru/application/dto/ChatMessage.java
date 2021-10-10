package innogl.ru.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private UUID id;
    private UUID chatId;
    private UUID senderId;
    private MessageType type;
    private String content;
}
