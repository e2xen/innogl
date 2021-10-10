package innogl.ru.application.mapper;

import innogl.ru.application.dto.UserCredentialDTO;
import innogl.ru.application.model.UserCredential;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCredentialMapper {
    UserCredentialDTO credentialToDTO(UserCredential credential);
}
