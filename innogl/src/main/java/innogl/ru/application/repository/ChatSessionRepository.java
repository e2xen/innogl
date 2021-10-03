package innogl.ru.application.repository;

import innogl.ru.application.model.ChatSession;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatSessionRepository extends KeyValueRepository<ChatSession, UUID> {

}
