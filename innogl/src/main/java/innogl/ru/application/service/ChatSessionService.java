package innogl.ru.application.service;

import com.google.common.collect.Lists;
import innogl.ru.application.dto.NewChatDTO;
import innogl.ru.application.dto.RegisterChatDTO;
import innogl.ru.application.mapper.ChatSessionMapper;
import innogl.ru.application.model.ChatSession;
import innogl.ru.application.repository.ChatSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<String> getTopics() {
        List<ChatSession> chats = Lists.newArrayList(chatSessionRepository.findAll());
        return chats.stream().map(ChatSession::getTopic)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
