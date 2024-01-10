package ua.testing.user_service.utils.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.testing.user_service.utils.FileSystemPropertyProvider;

import java.util.List;

@Component
public class ServerPropertyProviderImpl implements FileSystemPropertyProvider {
    @Value("${esprit.filesystem.repository.root}")
    private String fileSystemRepositoryRootFolder;

    @Value("#{'${esprit.filesystem.repository.entities}'.split(',')}")
    private List<String> fileSystemRepositoryEntities;

    @Override
    public String getFileSystemRepositoryRootFolder() {
        return fileSystemRepositoryRootFolder;
    }

    @Override
    public List<String> getFileSystemRepositoryEntities() {
        return fileSystemRepositoryEntities;
    }
}
