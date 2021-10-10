package innogl.ru.application.service;

import innogl.ru.application.dto.NewChatDTO;
import innogl.ru.application.dto.RegisterChatDTO;

import java.util.List;
import java.util.UUID;

public interface ChatSessionApiService {
    NewChatDTO createOrFindChatSession(RegisterChatDTO registerDTO);
    List<String> getTopics();
    boolean isMemberOfChatSessionWithId(String userToken, UUID chatSessionId);
    boolean isChatFull(UUID chatSessionId);
    void addUserToChat(String userToken, UUID chatId);
}
