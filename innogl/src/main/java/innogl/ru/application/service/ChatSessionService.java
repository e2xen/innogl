package innogl.ru.application.service;

import innogl.ru.application.dto.NewChatDTO;
import innogl.ru.application.dto.RegisterChatDTO;
import innogl.ru.application.mapper.ChatSessionMapper;
import innogl.ru.application.model.ChatSession;
import innogl.ru.application.repository.ChatSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatSessionService {

    private final ChatSessionRepository chatSessionRepository;
    private final ChatSessionMapper mapper;

    public NewChatDTO createChatSession(RegisterChatDTO registerDTO, String userSession) {
        return mapper.toNewChatDTO(
                chatSessionRepository.save(new ChatSession(userSession, registerDTO.getTopic()))
        );
    }
}
