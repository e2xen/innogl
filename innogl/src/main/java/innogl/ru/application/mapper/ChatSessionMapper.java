package innogl.ru.application.mapper;

import innogl.ru.application.dto.NewChatDTO;
import innogl.ru.application.model.ChatSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatSessionMapper {

    NewChatDTO toNewChatDTO(ChatSession chatSession);
}
