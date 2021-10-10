package innogl.ru.application.helper;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UUIDHelper {

    private static final String UUID_PATTERN = "\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b";

    public static UUID extractUUIDFromPath(String path) {
        if (path == null) {
            return null;
        }
        Pattern pattern = Pattern.compile(UUID_PATTERN);
        Matcher matcher = pattern.matcher(path);
        return matcher.find() ? UUID.fromString(matcher.group()) : null;
    }

    public static boolean matchesPathWithUUID(String target, String formattedPath) {
        return target != null && formattedPath != null &&
                target.matches(String.format(formattedPath, UUID_PATTERN));
    }
}
