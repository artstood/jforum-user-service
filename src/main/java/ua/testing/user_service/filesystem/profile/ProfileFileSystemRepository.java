package ua.testing.user_service.filesystem.profile;

import java.io.File;

public interface ProfileFileSystemRepository {

    File getProfileImage(Long userId, String imageName);
    String storeMedia(Long profileId, byte[] media);

    void deleteProfileMedia(Long profileId, String fileName);
}
