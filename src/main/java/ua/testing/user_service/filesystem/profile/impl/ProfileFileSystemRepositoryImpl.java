package ua.testing.user_service.filesystem.profile.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import ua.testing.user_service.enumeration.filesystem.FileTypes;
import ua.testing.user_service.enumeration.filesystem.ProfileResource;
import ua.testing.user_service.exception.filesystem.FileSystemRepositoryException;
import ua.testing.user_service.filesystem.FileSystemRepository;
import ua.testing.user_service.filesystem.profile.ProfileFileSystemRepository;
import ua.testing.user_service.utils.Constants;
import ua.testing.user_service.utils.FileSystemPropertyProvider;
import ua.testing.user_service.utils.FileSystemUtils;
import ua.testing.user_service.utils.StringUtils;

import java.io.File;
import java.io.IOException;


@Component
public class ProfileFileSystemRepositoryImpl extends FileSystemRepository implements ProfileFileSystemRepository {
    private static final String USER_FILE_SYSTEM = "users";
    @Autowired
    public ProfileFileSystemRepositoryImpl(FileSystemPropertyProvider fsProperty,
                                           FileSystemUtils fsUtils,
                                           ResourceLoader resourceLoader,
                                           StringUtils stringUtils) {
        super(fsProperty, fsUtils, resourceLoader, stringUtils);
    }

    @Override
    public File getProfileImage(Long userId, String imageName) {
        return retractFile(USER_FILE_SYSTEM, userId, imageName);
    }

    @Override
    public String storeMedia(Long profileId, byte[] media) {
        File mediaPath = storeFile(USER_FILE_SYSTEM, profileId, FileTypes.PNG, media);
        return mediaPath.getName();
    }

    @Override
    public void deleteProfileMedia(Long profileId, String fileName) {
        deleteMedia(USER_FILE_SYSTEM, profileId, fileName);
    }
}
