package ua.testing.user_service.filesystem.profile;

import ua.testing.user_service.enumeration.filesystem.ProfileMedia;

import java.io.File;

public interface ProfileFileSystemRepository {

    File getProfileImage(Long userId, String imageName);

    File getDefaultImage(ProfileMedia defaultImage);
}
