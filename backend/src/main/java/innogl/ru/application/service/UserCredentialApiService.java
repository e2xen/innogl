package innogl.ru.application.service;

import innogl.ru.application.dto.UserCredentialDTO;

import java.util.UUID;

public interface UserCredentialApiService {
    UserCredentialDTO newUser();
    UUID findIdByToken(String token);
    UUID ensureUser(UUID userId);
}
