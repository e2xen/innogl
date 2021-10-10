package innogl.ru.application.web;

import innogl.ru.application.BaseSpringTest;
import innogl.ru.application.dto.UserCredentialDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserControllerTest extends BaseSpringTest {

    @Test
    public void shouldCreateNewUser() {
        // given

        // when
        ResponseEntity<UserCredentialDTO> response = restTemplate.exchange(
                "/api/user",
                HttpMethod.POST,
                null,
                UserCredentialDTO.class
        );

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getUserId());
        assertNotNull(response.getBody().getSecretToken());
    }
}
