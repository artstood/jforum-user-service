package ua.testing.user_service.utils.impl;

import org.springframework.stereotype.Component;
import ua.testing.user_service.utils.LocalDateTimeUtils;

import java.time.LocalDate;

@Component
public class LocalDateTimeUtilsImpl implements LocalDateTimeUtils {

    @Override
    public LocalDate getTodayDate() {
        return LocalDate.now();
    }
}
