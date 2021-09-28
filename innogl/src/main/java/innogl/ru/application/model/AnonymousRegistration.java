package innogl.ru.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.UUID;

@RedisHash("anonymousRegistration")
@NoArgsConstructor
@AllArgsConstructor
public class AnonymousRegistration {
    @Id
    @Getter
    @Setter
    private UUID id = UUID.randomUUID();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter
    @Setter
    private String userSessionId;

    @Getter
    @Setter
    private LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(5);

    @Getter
    @Setter
    @Nullable
    private String topic = null;

    public AnonymousRegistration(String userSessionId) {
        this.userSessionId = userSessionId;
    }

    public AnonymousRegistration(String userSessionId, String topic) {
        this.userSessionId = userSessionId;
        this.topic = topic;
    }
}
