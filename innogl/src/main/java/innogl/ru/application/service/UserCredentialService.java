package innogl.ru.application.service;

import innogl.ru.application.dto.UserCredentialDTO;
import innogl.ru.application.mapper.UserCredentialMapper;
import innogl.ru.application.model.UserCredential;
import innogl.ru.application.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserCredentialService implements UserCredentialApiService {
    private final UserCredentialRepository repository;
    private final UserCredentialMapper mapper;

    public UserCredentialDTO newUser() {
        UserCredential user = repository.save(new UserCredential());
        return mapper.credentialToDTO(user);
    }

    public UUID findIdByToken(String token) {
        return repository.findBySecretToken(token)
                .map(UserCredential::getUserId)
                .orElseThrow(() -> new NotFoundException("User id by token is not found"));
    }

    public UUID ensureUser(UUID userId) {
        return repository.findById(userId)
                .map(UserCredential::getUserId)
                .orElseThrow(() -> new NotFoundException("User by id is not found"));
    }
}
