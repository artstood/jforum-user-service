package ua.testing.user_service.utils.impl;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import ua.testing.user_service.utils.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class StringUtilsImpl implements StringUtils {
    @Override
    public String randomString(int length) {
        return RandomStringUtils.random(length, true, true);
    }

    @Override
    public String sha256Encode(String str) {
        return Hashing.sha256()
                .hashString(str, StandardCharsets.UTF_8)
                .toString();
    }

    @Override
    public boolean isEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.isEmpty(str);
    }

    @Override
    public String randomUUID() {
        return UUID.randomUUID().toString();
    }
}
