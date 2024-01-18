package ua.testing.user_service.exception.service.profile;

import ua.testing.user_service.enumeration.filesystem.ProfileResource;
import ua.testing.user_service.model.profile.Profile;
import ua.testing.user_service.model.profile.ProfileData;

import java.io.File;
import java.time.LocalDate;

public interface ProfileService {
    Profile createNewProfile(LocalDate birtDate);

    Profile updateProfileMedia(ProfileData oldProfile, Profile userProfile, byte[] avatar, byte[] banner);

    File getProfileResource(Long userId, ProfileResource profileResource);
}
