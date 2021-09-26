package innogl.ru.application.repository;

import innogl.ru.application.model.AnonymousRegistration;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnonymousRegistrationRepository extends KeyValueRepository<AnonymousRegistration,String> {
    List<AnonymousRegistration> findAllByUserSessionId(String userSessionId);
}
