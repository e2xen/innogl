package innogl.ru.application.repository;

import innogl.ru.application.model.UserCredential;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserCredentialRepository extends KeyValueRepository<UserCredential, UUID>  {
    Optional<UserCredential> findBySecretToken(String token);
}
