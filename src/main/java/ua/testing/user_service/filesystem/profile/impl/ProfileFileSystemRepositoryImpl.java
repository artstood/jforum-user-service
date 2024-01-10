package ua.testing.user_service.filesystem.profile.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.testing.user_service.enumeration.filesystem.ProfileMedia;
import ua.testing.user_service.filesystem.FileSystemRepository;
import ua.testing.user_service.filesystem.profile.ProfileFileSystemRepository;
import ua.testing.user_service.utils.Constants;
import ua.testing.user_service.utils.FileSystemPropertyProvider;
import ua.testing.user_service.utils.FileSystemUtils;

import java.io.File;

@Component
public class ProfileFileSystemRepositoryImpl extends FileSystemRepository implements ProfileFileSystemRepository {

    private static final String USER_FILE_SYSTEM = "user";
    private static final String DEFAULT_RESOURCE_PATH = "src/main/resources/media_default/user/";

    @Autowired
    public ProfileFileSystemRepositoryImpl(FileSystemPropertyProvider fsProperty, FileSystemUtils fsUtils) {
        super(fsProperty, fsUtils);
    }

    @Override
    public File getProfileImage(Long userId, String imageName) {
        return retractFile(USER_FILE_SYSTEM, userId, imageName);
    }


    @Override
    public File getDefaultImage(ProfileMedia defaultImage) {
        return new File(DEFAULT_RESOURCE_PATH + defaultImage.getFileName() + Constants.Extensions.PNG);
    }
}
