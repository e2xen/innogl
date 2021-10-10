package innogl.ru.application.web;

import innogl.ru.application.BaseSpringTest;
import innogl.ru.application.dto.NewChatDTO;
import innogl.ru.application.dto.RegisterChatDTO;
import innogl.ru.application.model.ChatSession;
import innogl.ru.application.model.UserCredential;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ChatSessionControllerTest extends BaseSpringTest {

    @Test
    public void shouldCreateNewChatSession() {
        // given
        UUID userId = UUID.randomUUID();
        userCredentialRepository.save(new UserCredential(userId, "token"));

        // when
        RegisterChatDTO registerChatDTO = new RegisterChatDTO(userId, null);
        ResponseEntity<NewChatDTO> response = restTemplate.exchange(
                "/api/chat-session",
                HttpMethod.POST,
                new HttpEntity<>(registerChatDTO),
                NewChatDTO.class
        );

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userId, response.getBody().getNewUserId());
        assertNotNull(response.getBody().getChatId());
        assertFalse(response.getBody().isFull());
    }

    @Test
    public void shouldRetrieveAllTopics() {
        ChatSession session1 = new ChatSession(UUID.randomUUID(), new HashSet<>(), OffsetDateTime.now(), "topic1", false);
        ChatSession session2 = new ChatSession(UUID.randomUUID(), new HashSet<>(), OffsetDateTime.now(), null, false);
        ChatSession session3 = new ChatSession(UUID.randomUUID(), new HashSet<>(), OffsetDateTime.now(), "topic3", false);

        chatSessionRepository.saveAll(List.of(session1, session2, session3));

        ResponseEntity<List> response = restTemplate.exchange(
                "/api/topics",
                HttpMethod.GET,
                null,
                List.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Set<String> topics = new HashSet<>((List<String>) response.getBody());
        assertEquals(Set.of("topic1", "topic3"), topics);
    }
}
