package innogl.ru.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatSession {
    public static final int MAX_NUMBER_OF_USERS = 2;

    @Id
    private UUID chatId = UUID.randomUUID();

    private Set<UUID> userIds = new HashSet<>();

    private OffsetDateTime expirationTime = OffsetDateTime.now().plusHours(1);

    @Nullable
    private String topic = null;

    @Indexed
    private boolean full = false;


    public ChatSession(UUID userId, @Nullable String topic) {
        userIds.add(userId);
        this.topic = topic;
    }

    public ChatSession addUser(UUID userId) {
        if (userIds.size() >= MAX_NUMBER_OF_USERS) {
            throw new IllegalStateException("Max number of users in chat exceeded: " + MAX_NUMBER_OF_USERS);
        }
        userIds.add(userId);
        if (userIds.size() == MAX_NUMBER_OF_USERS) {
            full = true;
        }
        return this;
    }
}
