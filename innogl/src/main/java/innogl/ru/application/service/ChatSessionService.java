package innogl.ru.application.service;

import com.google.common.collect.Lists;
import innogl.ru.application.dto.NewChatDTO;
import innogl.ru.application.dto.RegisterChatDTO;
import innogl.ru.application.mapper.ChatSessionMapper;
import innogl.ru.application.model.ChatSession;
import innogl.ru.application.repository.ChatSessionRepository;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.core.DestinationResolutionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatSessionService {

    private final ChatSessionRepository repository;
    private final ChatSessionMapper mapper;
    UserCredentialService userCredentialService;

    public NewChatDTO createOrFindChatSession(RegisterChatDTO registerDTO) {
        UUID userId = userCredentialService.ensureUser(registerDTO.getUserId());

        String topic = Optional.ofNullable(registerDTO)
                .map(RegisterChatDTO::getTopic)
                .map(String::trim)
                .orElse(null);

        List<ChatSession> sessions = repository.findAllByFullFalse();
        log.info("findAllByFullFalse() = " + sessions.toString());

        ChatSession chatSession;
        if (StringUtil.isNullOrEmpty(topic) && !sessions.isEmpty()) {
            chatSession = sessions.get(0);
        } else {
            chatSession = repository.save(new ChatSession(userId, topic));
        }

        return mapper.chatSessionToNewChatDTO(chatSession, userId);
    }

    public List<String> getTopics() {
        List<ChatSession> chats = Lists.newArrayList(repository.findAll());
        return chats.stream().map(ChatSession::getTopic)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public boolean isMemberOfChatSessionWithId(String userToken, UUID chatSessionId) {
        Optional<ChatSession> chat = repository.findById(chatSessionId);
        return chat.isPresent() &&
                chat.get().getUserIds().contains(userCredentialService.findIdByToken(userToken));
    }

    public void addUserToChat(String userToken, UUID chatId) {
        UUID userId = userCredentialService.findIdByToken(userToken);
        Optional<ChatSession> optionalChat = repository.findById(chatId);
        if (optionalChat.isEmpty()) {
            throw new DestinationResolutionException("No chat by id: " + chatId.toString());
        }

        ChatSession chat = optionalChat.get();
        if (chat.getUserIds().contains(userId)) {
            return;
        }
        if (chat.isFull()) {
            throw new IllegalStateException("Chat by id is full: " + chatId);
        }

        chat.addUser(userId);
        repository.save(chat);
    }
}
