package innogl.ru.application.mapper;

import innogl.ru.application.dto.NewChatDTO;
import innogl.ru.application.model.ChatSession;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ChatSessionMapper {

    NewChatDTO toNewChatDTO(ChatSession chatSession);
}
