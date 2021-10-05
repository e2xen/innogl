package innogl.ru.application.service;

import innogl.ru.application.dto.NewChatDTO;
import innogl.ru.application.dto.RegisterChatDTO;
import innogl.ru.application.mapper.ChatSessionMapper;
import innogl.ru.application.model.ChatSession;
import innogl.ru.application.repository.ChatSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatSessionService {

    private final ChatSessionRepository chatSessionRepository;
    private final ChatSessionMapper mapper;

    public NewChatDTO createChatSession(RegisterChatDTO registerDTO, String userSession) {
        String topic = Optional.ofNullable(registerDTO)
                .map(RegisterChatDTO::getTopic)
                .orElse(null);

        ChatSession chatSession = chatSessionRepository.save(new ChatSession(userSession, topic));
        return mapper.toNewChatDTO(chatSession);
    }
}
