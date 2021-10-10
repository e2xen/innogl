package innogl.ru.application.interceptor;

import innogl.ru.application.helper.UUIDHelper;
import innogl.ru.application.service.ChatSessionService;
import innogl.ru.application.service.StompChatService;
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

import static innogl.ru.application.constants.StompHeaders.AUTH_HEADER;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatChannelInterceptor implements ChannelInterceptor {

    private final ChatSessionService chatSessionService;
    private final StompChatService stompChatService;

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
}
