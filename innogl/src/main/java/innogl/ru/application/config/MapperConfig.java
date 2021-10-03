package innogl.ru.application.config;

import innogl.ru.application.mapper.ChatSessionMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ChatSessionMapper chatSessionMapper() {
        return Mappers.getMapper(ChatSessionMapper.class);
    }
}
