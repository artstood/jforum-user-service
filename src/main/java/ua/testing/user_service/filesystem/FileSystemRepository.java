package ua.testing.user_service.filesystem;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import ua.testing.user_service.exception.filesystem.FileSystemRepositoryException;
import ua.testing.user_service.utils.FileSystemPropertyProvider;
import ua.testing.user_service.utils.FileSystemUtils;

import java.io.File;


@RequiredArgsConstructor
public abstract class FileSystemRepository {

    private final FileSystemPropertyProvider fsProperty;

    private final FileSystemUtils fsUtils;

    protected final ResourceLoader resourceLoader;

    protected File retractFile(String tableName, Long id, String fileName) {
        File file = fsUtils.createFile(
                fsProperty.getFileSystemRepositoryRootFolder(),
                tableName,
                id.toString(),
                fileName);
        if (!file.exists()) {
            throw new FileSystemRepositoryException();
        }

        return file;
    }
}
