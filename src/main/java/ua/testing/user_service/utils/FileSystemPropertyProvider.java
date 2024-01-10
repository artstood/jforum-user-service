package ua.testing.user_service.utils;

import java.util.List;

public interface FileSystemPropertyProvider {
    String getFileSystemRepositoryRootFolder();

    List<String> getFileSystemRepositoryEntities();
}
