package innogl.ru.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCredential {
    private static final int SECRET_TOKEN_LENGTH = 50;

    @Id
    private UUID userId = UUID.randomUUID();
    @Indexed
    private String secretToken = RandomStringUtils.randomAlphanumeric(SECRET_TOKEN_LENGTH);
}
