package innogl.ru.application.interceptor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.AccessException;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class ChatChannelInterceptor implements ChannelInterceptor {
    private static final String UUID_PATTERN = "\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b";

    @SneakyThrows
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("preSend() - message = {}", message.toString());
        final StompHeaderAccessor headers = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (headers != null && headers.getCommand() == StompCommand.SUBSCRIBE) {
            String dest = headers.getDestination();
            log.info("subscription to destination = {}", dest);
            if (dest != null && dest.matches(String.format("/chat/%s/queue/messages", UUID_PATTERN))) {
                log.info("performing subscription to a chat = {}", extractUUIDFromPath(dest));
                throw new AccessException("not allowed to join chat");
            } else {
                log.info("performing subscription to undefined");
            }
        }
        return message;
    }

    private static String extractUUIDFromPath(String path) {
        Pattern pattern = Pattern.compile(UUID_PATTERN);
        Matcher matcher = pattern.matcher(path);
        return matcher.find() ? matcher.group() : null;
    }
}
