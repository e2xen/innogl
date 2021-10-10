package innogl.ru.application.mapper;

import innogl.ru.application.dto.ChatDTO;
import innogl.ru.application.dto.NewChatDTO;
import innogl.ru.application.model.ChatSession;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ChatSessionMapper {

    ChatDTO chatSessionToChatDTO(ChatSession chatSession);
    NewChatDTO chatSessionToNewChatDTO(ChatSession chatSession, UUID newUserId);
}
