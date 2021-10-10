package innogl.ru.application.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import innogl.ru.application.dto.ChatMessage;
import innogl.ru.application.dto.MessageType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeserializationTests {

    @Test
    public void shouldDeserializeChatMessage_whenContainsEnumValue() throws JsonProcessingException {
        String mes = "{\"type\": \"USER\"}";
        ChatMessage chatMessage = new ObjectMapper().readValue(mes, ChatMessage.class);
        assertEquals(MessageType.USER, chatMessage.getType());
    }
}
