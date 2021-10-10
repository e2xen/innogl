package innogl.ru.application.integration;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class BaseIntegrationTest {

    protected static final GenericContainer redis;

    static {
        redis = new GenericContainer(DockerImageName.parse("redis:6.2.1-alpine"))
                .withExposedPorts(6379);
        redis.start();
    }

    @Test
    public void shouldRunRedisContainer() {
        assertTrue(redis.isRunning());
    }
}
