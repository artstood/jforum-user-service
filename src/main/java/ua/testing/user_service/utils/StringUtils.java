package ua.testing.user_service.utils;

public interface StringUtils {
    String randomString(int length);

    String sha256Encode(String str);

    boolean isEmpty(String str);

    String randomUUID();
}
