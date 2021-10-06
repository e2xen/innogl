package innogl.ru.application.interceptor;

import innogl.ru.application.helper.UUIDHelper;
import innogl.ru.application.service.ChatSessionService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatChannelInterceptor implements ChannelInterceptor {
    private static final String AUTH_HEADER = "Authorization";

    private final ChatSessionService chatSessionService;

    @SneakyThrows
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("preSend() - message = {}", message.toString());
        final StompHeaderAccessor headers = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (headers != null && headers.getCommand() == StompCommand.SUBSCRIBE) {
            preSubscribe(headers);
        }
        return message;
    }

    private void preSubscribe(StompHeaderAccessor headers) {
        UUID userId = UUID.fromString(Objects.requireNonNull(headers.getNativeHeader(AUTH_HEADER)).get(0));
        String dest = headers.getDestination();
        if (UUIDHelper.matchesPathWithUUID(dest, "/chat/%s/queue/messages")) {
             chatSessionService.addUserToChat(userId, UUIDHelper.extractUUIDFromPath(dest));
        } else {
            throw new DestinationResolutionException("Undefined subscribe destination: " + dest);
        }
    }
}
