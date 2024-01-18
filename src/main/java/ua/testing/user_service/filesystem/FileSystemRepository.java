package ua.testing.user_service.filesystem;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import ua.testing.user_service.enumeration.filesystem.FileTypes;
import ua.testing.user_service.exception.filesystem.FileSystemRepositoryException;
import ua.testing.user_service.utils.FileSystemPropertyProvider;
import ua.testing.user_service.utils.FileSystemUtils;
import ua.testing.user_service.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


@RequiredArgsConstructor
@Slf4j
public abstract class FileSystemRepository {

    private final FileSystemPropertyProvider fsProperty;

    private final FileSystemUtils fsUtils;

    protected final ResourceLoader resourceLoader;

    protected final StringUtils stringUtils;

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

    protected File storeFile(String tableName, Long id, FileTypes type, byte[] fileBytes) {
        try {
            File writeDest = fsUtils.pathOf(
                    fsProperty.getFileSystemRepositoryRootFolder(),
                    tableName,
                    id.toString()).toFile();
            if (!writeDest.exists()) {
                fsUtils.mkDirs(writeDest.toPath());
            }

            return fsUtils.writeBytes(writeDest.toPath().resolve(stringUtils.randomUUID() + type.getExtension()), fileBytes);
        } catch (IOException e) {
            throw new FileSystemRepositoryException("cannot store provided files");
        }
    }

    protected void deleteMedia(String tableName, Long id, String fileName) {
        Path deletionPath = fsUtils.pathOf(
                fsProperty.getFileSystemRepositoryRootFolder(),
                tableName,
                id.toString(),
                fileName);
        try {
            fsUtils.delete(deletionPath);
        } catch (IOException e) {
            log.warn("Cannot delete file: {}", deletionPath);
        }
    }
}
