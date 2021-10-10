package innogl.ru.application;

import innogl.ru.application.integration.BaseIntegrationTest;
import innogl.ru.application.repository.ChatSessionRepository;
import innogl.ru.application.repository.UserCredentialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {BaseSpringTest.Initializer.class})
public abstract class BaseSpringTest extends BaseIntegrationTest {

	@Test
	void contextLoads() {
	}

	@Autowired
	protected ChatSessionRepository chatSessionRepository;
	@Autowired
	protected UserCredentialRepository userCredentialRepository;

	@BeforeEach
	private void before() {
		resetRepository(chatSessionRepository);
		resetRepository(userCredentialRepository);
	}

	private static void resetRepository(KeyValueRepository<?, ?> repository) {
		repository.deleteAll();
	}

	@Autowired
	protected TestRestTemplate restTemplate;



	static class Initializer
			implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			TestPropertyValues.of(
					"spring.redis.host=" + redis.getHost(),
					"spring.redis.port=" + redis.getFirstMappedPort()
			).applyTo(configurableApplicationContext.getEnvironment());
		}
	}
}
