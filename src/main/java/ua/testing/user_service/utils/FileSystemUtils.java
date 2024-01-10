package ua.testing.user_service.utils;

import java.io.File;
import java.nio.file.Path;

public interface FileSystemUtils {
    File createFile(String filename);

    File createFile(Path filePath);

    File createFile(String first, String... path);

    void mkDir(String dirPath);

    void mkDirs(Path dirsPath);

    void mkDirs(String first, String... path);

    Path pathOf(String first, String... path);
}
