package innogl.ru.application.service;

import innogl.ru.application.dto.ChatMessage;

import java.util.UUID;

public interface StompChatApiService {
    void sendMessage(String userToken, ChatMessage chatMessage);
    void sendStartMessageToChat(UUID chatId);
    void sendEndMessageToChat(UUID chatId);
}
