package ua.testing.user_service.utils.impl;

import org.springframework.stereotype.Component;
import ua.testing.user_service.exception.utils.FileSystemUtilsException;
import ua.testing.user_service.utils.FileSystemUtils;

import java.io.File;
import java.nio.file.Path;

@Component
public class FileSystemUtilsImpl implements FileSystemUtils {
    private static final String FOLDER_ALREADY_EXIST = "Cannot create folder under path %s as it already exist";

    @Override
    public File createFile(String filename) {
        return new File(filename);
    }

    @Override
    public File createFile(Path filePath) {
        return filePath.toFile();
    }

    @Override
    public File createFile(String first, String... path) {
        return pathOf(first, path).toFile();
    }

    @Override
    public void mkDir(String dirPath) {
        final File dir = createFile(dirPath);
        if (!dir.mkdir()) {
            throw new FileSystemUtilsException(String.format(FOLDER_ALREADY_EXIST, dir.getAbsolutePath()));
        }
    }

    @Override
    public void mkDirs(Path dirsPath) {
        final File dir = createFile(dirsPath);
        if (!dir.mkdirs()) {
            throw new FileSystemUtilsException(String.format(FOLDER_ALREADY_EXIST, dir.getAbsolutePath()));
        }
    }

    @Override
    public void mkDirs(String first, String... path) {
        final File dir = pathOf(first, path).toFile();
        if (!dir.mkdirs()) {
            throw new FileSystemUtilsException(String.format(FOLDER_ALREADY_EXIST, dir.getAbsolutePath()));
        }
    }

    @Override
    public Path pathOf(String first, String... path) {
        return Path.of(first, path);
    }
}