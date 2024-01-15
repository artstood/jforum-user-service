package ua.testing.user_service.filesystem.profile.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import ua.testing.user_service.enumeration.filesystem.ProfileMedia;
import ua.testing.user_service.exception.filesystem.FileSystemRepositoryException;
import ua.testing.user_service.filesystem.FileSystemRepository;
import ua.testing.user_service.filesystem.profile.ProfileFileSystemRepository;
import ua.testing.user_service.utils.Constants;
import ua.testing.user_service.utils.FileSystemPropertyProvider;
import ua.testing.user_service.utils.FileSystemUtils;

import java.io.File;
import java.io.IOException;

@Component
public class ProfileFileSystemRepositoryImpl extends FileSystemRepository implements ProfileFileSystemRepository {
    private static final String USER_FILE_SYSTEM = "user";
    private static final String DEFAULT_RESOURCE_PATH = "classpath:media_default/user"; //todo temp solution

    @Autowired
    public ProfileFileSystemRepositoryImpl(FileSystemPropertyProvider fsProperty, FileSystemUtils fsUtils, ResourceLoader resourceLoader) {
        super(fsProperty, fsUtils, resourceLoader);
    }

    @Override
    public File getProfileImage(Long userId, String imageName) {
        return retractFile(USER_FILE_SYSTEM, userId, imageName);
    }


    @Override
    public File getDefaultImage(ProfileMedia defaultImage) {
        try {
            return
                    resourceLoader
                            .getResource(DEFAULT_RESOURCE_PATH)
                            .getFile()
                            .toPath()
                            .resolve(defaultImage.getFileName() + Constants.Extensions.PNG).toFile();
        } catch (IOException e) {
            throw new FileSystemRepositoryException(e);
        }
    }
}
