package ua.testing.user_service.service.profile;

import ua.testing.user_service.model.profile.Profile;

import java.io.File;
import java.time.LocalDate;

public interface ProfileService {
    Profile createNewProfile(LocalDate birtDate);

    File getAvatar(Long userId, boolean getDefault);

    File getBanner(Long userId, boolean getDefault);
}
