package innogl.ru.application.interceptor;

import innogl.ru.application.helper.UUIDHelper;
import innogl.ru.application.service.ChatSessionApiService;
import innogl.ru.application.service.StompChatApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.core.DestinationResolutionException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

import static innogl.ru.application.constants.StompHeaders.AUTH_HEADER;
import static innogl.ru.application.constants.StompHeaders.CHAT_ID_HEADER;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatChannelInterceptor implements ChannelInterceptor {

    private final ChatSessionApiService chatSessionService;
    private final StompChatApiService stompChatService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("preSend() - message = {}", message.toString());
        final StompHeaderAccessor headers = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (headers != null && headers.getCommand() == StompCommand.SUBSCRIBE) {
            preSubscribe(headers);
        }
        return message;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        log.info("postSend() - message = {}", message.toString());
        if (!sent) {
            return;
        }
        final StompHeaderAccessor headers = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (headers != null && headers.getCommand() == StompCommand.SUBSCRIBE) {
            postSubscribe(headers);
        }
        if (headers != null && headers.getCommand() == StompCommand.UNSUBSCRIBE) {
            postUnsubscribe(headers);
        }
    }

    private void preSubscribe(StompHeaderAccessor headers) {
        String userToken = Objects.requireNonNull(headers.getNativeHeader(AUTH_HEADER)).get(0);
        String dest = headers.getDestination();
        if (UUIDHelper.matchesPathWithUUID(dest, "/chat/%s/queue/messages")) {
             chatSessionService.addUserToChat(userToken, UUIDHelper.extractUUIDFromPath(dest));
        } else {
            throw new DestinationResolutionException("Undefined subscribe destination: " + dest);
        }
    }

    private void postSubscribe(StompHeaderAccessor headers) {
        String dest = headers.getDestination();
        if (UUIDHelper.matchesPathWithUUID(dest, "/chat/%s/queue/messages")) {
            stompChatService.sendStartMessageToChat(UUIDHelper.extractUUIDFromPath(dest));
        } else {
            throw new DestinationResolutionException("Undefined subscribe destination: " + dest);
        }
    }

    private void postUnsubscribe(StompHeaderAccessor headers) {
        String userToken = Objects.requireNonNull(headers.getNativeHeader(AUTH_HEADER)).get(0);
        UUID chatId = UUID.fromString(Objects.requireNonNull(headers.getNativeHeader(CHAT_ID_HEADER)).get(0));
        if (chatSessionService.isMemberOfChatSessionWithId(userToken, chatId)) {
            stompChatService.sendEndMessageToChat(chatId);
        } else {
            throw new IllegalArgumentException("You are not a member of the chat");
        }
    }
}
