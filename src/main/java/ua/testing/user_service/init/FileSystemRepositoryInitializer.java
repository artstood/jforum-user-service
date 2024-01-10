package ua.testing.user_service.init;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import ua.testing.user_service.utils.FileSystemPropertyProvider;
import ua.testing.user_service.utils.FileSystemUtils;

import java.io.File;

@RequiredArgsConstructor
public class FileSystemRepositoryInitializer implements InitializingBean {
    private final FileSystemPropertyProvider fileSystemPropertyProvider;

    private final FileSystemUtils fileSystemUtils;

    @Override
    public void afterPropertiesSet() {
        final String fsRootDirPath = fileSystemPropertyProvider.getFileSystemRepositoryRootFolder();
        final File fsRoot = fileSystemUtils.createFile(fsRootDirPath);

        if (!fsRoot.exists()) {
            fileSystemPropertyProvider
                    .getFileSystemRepositoryEntities()
                    .forEach(entity -> fileSystemUtils.mkDirs(fsRootDirPath, entity));
        }
    }
}
