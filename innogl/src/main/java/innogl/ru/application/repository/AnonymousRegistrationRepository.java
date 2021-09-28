package innogl.ru.application.repository;

import innogl.ru.application.model.AnonymousRegistration;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnonymousRegistrationRepository extends KeyValueRepository<AnonymousRegistration,String> {
    Optional<AnonymousRegistration> findByUserSessionId(String userSessionId);

}
